package com.TgBotMOEVM.handler;

import com.TgBotMOEVM.constant.ButtonCommand;
import com.TgBotMOEVM.model.ExamInfo;
import com.TgBotMOEVM.repository.UserRepository;
import com.TgBotMOEVM.service.ExamService;
import com.TgBotMOEVM.service.UserService;
import com.TgBotMOEVM.service.impl.ExamServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class ExamScheduleHandler implements Handler {

    private final UserRepository userRepository;

    private static final Pattern EXAM_PATTERN = Pattern.compile("^\\\\s*[1-9]\\\\d{3}\\\\s*$");

    private final ExamService examService;

    @Override
    public List<BotApiMethod<?>> handle(Update update) {
        List<BotApiMethod<?>> responses = new ArrayList<>();

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String messageText = message.getText();
            Long chatId = message.getChatId();

            String examArg = messageText.substring("Экзамены".length()).trim();
            StringBuilder stringBuilder = new StringBuilder();
            if (!EXAM_PATTERN.matcher(examArg).matches()){
                responses.add(createMessage(chatId.toString(), "Неверный формат номера группы."));
                return responses;
            }
            if (!examArg.isEmpty() &&
                    userRepository.findByTelegramId(message.getChatId().toString()).get().getUserinfo() != null) {
                try {
                    List<ExamInfo> exams = examService.getExams(examArg);
                    if (exams.isEmpty()){
                        stringBuilder.append("Нет информации об экзаменах.");
                    }
                    else{
                        stringBuilder.append("Экзамены текущего семестра группы "
                                + examArg + ":\n");

                        for (ExamInfo exam : exams) {
                            stringBuilder.append(String.format("\nЭкзамен по: \"%s\", " +
                                            " будет вести преподаватель  %s" +
                                            "\nв %s\t%s в Аудитории: \"%s\"",
                                    exam.getName(), exam.getTeacher(),
                                    exam.getStartTime(), exam.getDate(),
                                    exam.getRoom())).append("\n");
                        }
                        stringBuilder.append("\nУспехов!");
                    }

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                responses.add(createMessage(chatId.toString(), stringBuilder.toString()));
            } else if (examArg.isEmpty()){
                responses.add(createMessage(chatId.toString(), "Пожалуйста, введите номер группы " +
                        "после команды 'Экзамены'."));
            } else {
                responses.add(createMessage(chatId.toString(), "Пожалуйста, авторизуйтесь в ETU ID."));
            }
        }

        return responses;
    }


    @Override
    public BotCommand getCommandObject() {
        return ButtonCommand.EXAM_SCHEDULE;
    }
}