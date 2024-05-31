package com.TgBotMOEVM;

import com.TgBotMOEVM.model.Handbook;
import com.TgBotMOEVM.repository.HandbookRepository;
import com.TgBotMOEVM.service.HandbookService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TgBotMOEVM {

	public static void main(String[] args) {
		SpringApplication.run(TgBotMOEVM.class, args);
	}

	@Bean
	CommandLineRunner run(HandbookService handbookService){

		return args -> {

			Handbook health1 = new Handbook("Здравпункт", "Здоровье",
					"""
							Здравпункт СПбГЭТУ «ЛЭТИ» находится в корпусе D, 2-й подъезд,
							2-й этаж (напротив входа в 5-й корпус).
							В здравпункте университета также проводят прививки и мероприятия по профилактике инфекционных заболеваний.
							       
							""");
			Handbook health2 = new Handbook("Поликлиники", "Здоровье",
					"""
							Поликлиники для граждан РФ
							По месту регистрации в общежитии:
							Общежитие № 1,2,3,4 – поликлиника №14 (ПО №13) (ул. Тобольская, д. 4)\s
							Общежитие № 5 – поликлиника № 75 (ул. Кузнецовская, д.9)\s
							Общежитие № 6 – поликлиника № 32 (пер. Вяземский, д. 3)
							Общежитие № 7 – поликлиника № 98 (Серебристый бульвар, д.14, к. 1)
							Общежитие № 8 – поликлиника № 49 (Ланская ул., д. 12)
							Общежитие №10,11 – поликлиника №23 (ПО №20)(ул. кронштадтская д.11)
							       
							""");
			Handbook health3 = new Handbook("Для иностранцев", "Здоровье",
					"""
							Врач для иностранцев. Алгоритм действий
							У иностранцев должен быть оформлен ДМС. На полисе написано два телефона, по одному из которых нужно позвонить и описать оператору проблему. Оператор скажет, куда и когда приходить.
							       
							""");

			Handbook education1 = new Handbook("Курсовые","Об учёбе",
					"""
							Курсовая
							Это как большая практическая или лабораторная работа. Она даётся 1 за семестр по 1 предмету, всего их может быть по 2 предметам за семестр по учебному плану. Это может быть чуть-чуть пугающим, но это решаемо, потому что по-другому не бывает, не переживайте.
							       
							""");
			Handbook education2 = new Handbook("Зачеты","Об учёбе",
					"""
							Зачеты
							Это вид оценки, которая выставляется до сессии. Есть зачёт с оценкой - можно получить 2, 3, 4 или 5. И обычный зачёт - можно получить только “зачёт” или “незачёт”. В учебном плане написано, какой вид оценивания у разных предметов.
							         Зачёты нужно проставить в зачётную книжку до конца зачётной недели.\s
							         							       
							""");
			Handbook education3 = new Handbook("Экзамены","Об учёбе",
					"""
							Это просто экзамены. Они проходят во время сессии. Расписание экзаменов обсуждает группа и староста приносит его в диспетчерскую.
							Между экзаменами обычно оставляют 3 дня: если экзамен 1-го числа, то следующий 5-го (3 дня не считая дней экзаменов). Преподаватели всё важное скажут в начале семестра, главное слушать.
							 							       
							""");
			Handbook education4 = new Handbook("Зачётная неделя","Об учёбе",
					"""
       						Зачётная неделя
       						Это особенное время в последнюю учебную неделю перед сессией. Тогда уже нет пар, но есть преподаватели, вы и ваши зачётные книжки. Нужно пройти по всем и поставить зачёты, при необходимости - досдать долги.
       						В последний день зачётной недели вечером выставляются оценки в ведомость. Если у вас нет зачёта до этого момента - вы попадаете на допсу.
       						""");
			Handbook education5 = new Handbook("День качества","Об учёбе",
					"""
       						День качества
							Последний день сессии, на котором можно исправить 1 оценку, чтобы изменить статус студента:
							 - отличник (все 5);
							 - ударник (4 и 5);
							 - хорошист (только 4);
							 - троечник (3, 4, 5);
							 - двоечник (2, 3, 4, 5 или есть неявки на экзамен);
							 - недопуск к доп.сессии (5 и больше неявок или оценок 2);
							
							Можно идти на день качества если:
							 - единственная оценка 4 и все остальные 5 (с ударника в отличника);
							 - все оценки 4 (с хорошиста в ударника);
							 - единственная оценка 3 и все остальные 4 и 5 (с троечника в хорошиста);
							 - единственная оценка 2 или неявка (с двоечника в что-нибудь получше);
							 - 5 двоек или неявок (из недопуска в двоечника).
							
							Во всех случаях, кроме последнего, на дне качества сдаётся предмет, по которому что-то единственное. В последнем случае студент вправе выбрать один из 5 долгов.
							Когда берёшь направление на ДК, оценка сгорает, и вместо неё проставляется новая, поэтому на дне качества можно как улучшить, так и ухудшить оценку.
							На день качества нужно направление в очном формате и список в дистанционном (это делает староста).
							ДК не считается пересдачей. Направления на дк остаются у преподавателя, а в зачётку ставится новая оценка.\s
							""");
			Handbook education6 = new Handbook("Доп. сессия","Об учёбе",
					"""
       						Доп. сессия
							Дополнительные 4 дня - неделя после сессии для ликвидации долгов (если их меньше пяти). Если после Дня качества у студента 5 и более долгов, то он не допущен до допсессии, и направляется на аттестационную комиссию.\s
							""");
			Handbook education7 = new Handbook("Комиссия","Об учёбе",
					"""
							Это ещё раз дополнительные сроки для ликвидации долгов, если к концу допсы есть долги или до допсы не допустили. На комиссию допускают:
							- Всех, у кого менее 4-х долгов;
							- Тех, у кого 4 долга, но только после собрания комиссии с преподавателями
							- с >4 долгов не всегда допускают.
							Перед пересдачей опять надо брать направления. После первой комиссии (срок обычно месяц) бывает вторая, но с ней уже на месте разберёмся.\s
							       
							""");

			Handbook places1 = new Handbook("Студенческая канцелярия", "Важные локации",
					"""
							Заказ справок любого вида осуществляется через личный кабинет студента в разделе «Дополнительное».
								               
							Примечание! Если Вам необходимо указать дополнительную информацию в справке, указывайте это в комментариях при заказе.
															
							Корпус 4, пом. 4204-4206
								+7 812 346-45-14
								studk@etu.ru
								
							Понедельник, среда	с 13:00 до 15:00
							Вторник, четверг	с 11:30 до 14:00
							Пятница	приема нет
								
								""");
			Handbook places2 = new Handbook("Приёмная комиссия", "Важные локации",
					"""
							Приёмная комиссия СПбГЭТУ «ЛЭТИ» отвечает за подготовку и опубликование информации о направлениях подготовки, на которые осуществляется набор на следующий учебный год, о количестве бюджетных и платных мест для поступающих, об условиях приёма на первый курс по каждой специальности, в том числе целевого приёма, о списке необходимых документов для поступления в университет.
							
							Сотрудники приёмной комиссии СПбГЭТУ «ЛЭТИ» ответят на все вопросы, связанные с приёмом в университет на 1-й курс и в магистратуру.      
							Пн - Пт с 10:00 до 17:00
							197022, Россия, Санкт-Петербург, ул. Профессора Попова, дом 5, корпус 5, пом. 5313
							+7 (800) 550-43-35, +7 (812) 655-13-57
							prcom@etu.ru
							""");
			Handbook places3 = new Handbook("Платное обучение", "Важные локации",
					"""
							Где узнать об оплате обучения?
							       
							Договоры на оказание платных образовательных услуг и дополнительные соглашения к нему заключаются в каб. 3245, 
							телефон +7 812 346-48-37, 
							адрес электронной почты ssc@etu.ru. 
														
							Вся информация об оплате обучения расположена на Сайте https://etu.ru/ru/studentam/platnoe-obuchenie
							     
							""");
			Handbook places4 = new Handbook("Общежитие", "Важные локации",
					"""
							Где решать вопросы с общежитием?
							       
							Информация по поселению:
							https://etu.ru/ru/vospitatelnaya-i-socialnaya/obshezhitiya/raspredelenie-studentov/
							     
							""");
			Handbook places5 = new Handbook("Иностранным гражданам", "Важные локации",
					"""
							Регистрация иностранных граждан
							
							Всем иностранцам после прибытия в Санкт-Петербург необходимо встать на миграционный учет. В течение первых суток со дня прибытия в Санкт-Петербург необходимо явиться в отдел по работе с иностранными учащимися (станция метро «Петроградская», ул. Профессора Попова, дом 5, корпус 3, кабинет 3423-1, с 11:00 до 16:00).
							При себе иметь:
							- Копию паспорта;
							- Копию миграционной карты;
							- Копию визы (при наличии);
							- Копию направления/контракта/договора (кроме I курса)
							В случае прибытия в выходной день, необходимо явиться в Международный Студенческий Офис в первый рабочий день после вашего прибытия.
							Управление расположено в 3 корпусе на 4 этаже, к. 3418, 3419, 3423a
							Доп информация по ссылке: https://etu.ru/ru/mezhdunarodnaya-deyatelnost/dekanat-riu/vizy-registraciya-i-strahovanie/
							Дактилоскопия для иностранных граждан: https://etu.ru/ru/mezhdunarodnaya-deyatelnost/dekanat-riu/pribytie-v-sankt-peterburg/prohozhdenie-obyazatelnoj-daktiloskopicheskoj-registracii-i-medicinskogo-osvidetelstvovaniya
							""");
			Handbook places6 = new Handbook("Библиотека", "Важные локации",
					"""
							Библиотека
							Волшебное место. Их много и на сайте об этом много и подробно написано.
							http://library.etu.ru/jirbis2/
							
							структура библиотеки http://library.etu.ru/jirbis2/index.php?option=com_content&view=article&id=13&Itemid=432;
							электронный каталог (цифровые версии любимых книжек)
							 http://library.etu.ru/jirbis2/index.php?option=com_irbis&view=irbis&Itemid=408
							 - в нём авторизация по читательскому билету (это то, на что вы будете фотографироваться в начале семестра): логин - фамилия, пароль - номер читательского.
							В читальных залах очень уютно. На 1 курсе (в первый месяц примерно) староста отведёт вас фотографироваться и брать учебники, но брать стоит только то что вы точно откроете, а фотографироваться лучше с тёмным верхом.
							     
							""");
			Handbook university1 = new Handbook("Ректор", "О структуре университета",
					"""
							Ректорат находится на втором этаже третьего корпуса. Там решаются все важные вопросы, связанные с университетом.
							Ректор - Виктор Николаевич Шелудько. Он - главное лицо нашего университета. Последнее слово всегда за ним. Помимо главенствующей должности, он так же занимает пост заведующего кафедрой систем автоматического управления.
							
							Проректорами университета являются:
							- Тупик Виктор Анатольевич. Проректор по научной работе. По совместительству - заведующий кафедрой микро радиоэлектроники и технологии радиоаппаратуры.
							- Минина Анастасия Андреевна. Проректор по международной деятельности. Именно она координирует и реализует проекты, связанные с международной и межрегиональной деятельностью.
							- Кустов Тарас Владимирович. Проректор по дополнительному образованию, заведующий кафедрой Защиты окружающей среды.
							- Иванова Ольга Витальевна - проректор по работе с молодёжью и связям с общественностью. Именно она курирует деятельность Отдела по связям с общественностью (в простонародье - ОСО).
							       
							""");
			Handbook university2 = new Handbook("Деканат", "О структуре университета",
					"""
							Деканат - это место, где:
							- Вы получите ответы на большую часть своих вопросов;
							- Староста заберёт журнал;
							- Вы сможете найти вашего зам.декана для выяснения каких-то неурядиц с оценками или преподавателями;
							- Вас сориентируют по вопросам перевода на другое направление/другой факультет/другую форму обучения;
							- Студентам выдают и подписывают студенческие билеты и зачетные книжки;
							- Вам выдадут направление на День качества/пересдачу/комиссию…
							
							Деканат ФКТИ вы найдёте в месте “стыка” 1-го и 2-го корпусов, в кабинете 2224. Приёмные дни: пн-пт. с 10:00 до 16:00 с перерывом на обед с 13:00 до 13:30.
							
							Деканом является Холод Иван Иванович. Он следит за организацией деятельности на всём факультете.
							
							У декана есть несколько заместителей:
							- Колпаков Андрей Сергеевич -- зам. декана по учебной работе со студентами 1 курса. Телефон -- +78122342746, почта -- askolpakov@etu.ru.
							- Халиуллин Ренат Альбинович -- зам. декана по учебной работе со студентами 2 курса. Почта -- rakhaliullin@etu.ru.
							- Миронов Сергей Эльмарович -- зам. декана по учебной работе со студентами 3-4 курсов. Почта -- semironov@etu.ru.
							- Павлов Сергей Михайлович -- зам. декана по учебной работе со студентами 5-6 курсов. Телефон -- +78122342746, почта -- smpavlov@etu.ru.
							- Андреев Валерий Сергеевич -- зам. декана по учебно-методической работе.
							- Цой Светлана Евгеньевна -- зам. декана по физическому воспитанию.
							- Ежов Сергей Николаевич -- зам. декана по научной работе.
							- Гайсина Светлана Валерьевна -- зам. декана по приему и профориентационной работе. Почта -- svgaysina@etu.ru.
							- Новожилов Игорь Михайлович -- зам. декана по работе с очно-заочной формой. Телефон +78122343937, почта imnovozhilov@etu.ru.
							- Новикова Евгения Сергеевна -- зам. декана по международной деятельности.
							
							Секретари:
							- Шмидт Нина Юрьевна. Специалист по учебно-методической работе. Почти всегда на месте, ответит на практически любой ваш вопрос.
							- Крупенко Наталья Борисовна. Старший диспетчер факультета. Она занимается оповещением старост о чём-либо посредством обзвона.
							           
							""");
			Handbook university3 = new Handbook("Кафедра МО ЭВМ", "О структуре университета",
					"""
							Кафедра математического обеспечения и применения ЭВМ (МоЭВМ)
							Главный кабинет: 3412
								Тел.для связи: +7 812 234-26-82
							Заведующий кафедрой - Кринкин Кирилл Владимирович
							
							Направления подготовки:
							Программная инженерия (учебный план https://etu.ru/sveden/education/eduOp/#09.04.04docs)
							Объекты профессиональной деятельности выпускников по направлению подготовки 231000 «Программная инженерия»: программный проект (проект разработки программного продукта); программный продукт (создаваемое программное обеспечение); процессы жизненного цикла программного продукта; методы и инструменты разработки программного продукта; персонал, участвующий в процессах жизненного цикла программного продукта.
							
							Прикладная математика и информатика (учебный план https://etu.ru/sveden/education/eduOp/#01.03.02docs)
							Направление ориентировано на подготовку специалистов, область профессиональной деятельности которых охватывает работу (в том числе научно-исследовательскую, аналитическую, проектную, производственно-технологическую и т.д.), связанную с использованием математики, программирования, информационно-коммуникационных технологий, компьютерных систем и сетей.
							Кафедра часто организовывает сторонние проекты или предлагает интересные соревнования, более подробно можно изучить или на сайте, или в группе вк.
							       
							""");
			Handbook university4 = new Handbook("Другие кафедры", "О структуре университета",
					"""
							Базовые кафедры, необходимые на первом курсе
							
							Кафедра иностранных языков (3-й корпус, 3-й этаж, к. 3319)
							Кафедра истории культуры, государства и права (5-й аудиторный корпус, 3-й этаж, к. 5325).
							Кафедра философии (5-й аудиторный корпус, 3-й этаж, к.к. 5329, 5330).
							Кафедра физики (3-й корпус, 1-й этаж, к. 3111).
							Кафедра физического воспитания и спорта (корпус С, к. С510, вход из 1 корпуса).
							       
							""");
			Handbook university5 = new Handbook("Профком", "О структуре университета",
					"""
							Профком
							Найти профком можно в помещении 3304. 
							Рабочие дни: пн. - пт. с 10:00 до 18:30, 
							приём с 11:00 до 17:00 (с 13:45 до 14:30 перерыв)
							Для обычного студента профком интересен тремя вещами - бесплатной печатью, материальной помощью и некоторыми плюшками в виде бесплатных экскурсий, скидок в театры, бесплатных билетов на спортивные матчи, некоторые бесплатные мероприятия.
						
							В профкоме можно напечатать лабораторную, курсовую или иной проект в объеме приблизительно 100 страниц.
							Однако стоит принять во внимание некоторые моменты: обычно в перерывах туда очень большие очереди (особенно в обед) и чтобы успеть распечатать нужно приезжать хотя бы минут на 30 раньше начала пары. Иногда страдает качество печати.
							
							Гайд:
							Для печати необходимо заранее прислать файлики на электронную почту profcomprint@mail.ru
							В теме письма указывается ФИО, номер группы и номер профсоюзного билета (например: Иванов Иван Петрович, гр.6308, профбилет 1987/6308).
							Приходить надо с профбилетом.
							
							Материальная помощь
							Профком может предоставить вам материальную помощь раз в семестр по довольно большому спектру категорий.
							Консультацию по мат.помощи можно получить в отделе по социальной работе по почте osrLETI@yandex.ru
							       
							Информацию о билетах, мероприятиях и прочих вещах лучше смотреть в группе профкома.
							       
							""");
			Handbook military = new Handbook("Военный стол","В главное меню",
					"""
							Военный стол. Аудитория 2329
							
							понедельник 11:00 – 14:00
							вторник 11:00 – 14:00
							среда приёма нет
							четверг 11:00 – 14:00
							пятница 11:00 – 14:00
							Всем студентам, поступившим впервые на бакалавриат и далее оформляется отсрочка от армии. Оформляется единожды и при отчислении/восстановлении пропадает.
							Для постановки на военный учёт и получения отсрочки:
							 1. Прибыть в военно-учетный стол университета (комн. 2329) и заполнить личную карточку. При себе иметь паспорт, удостоверение гражданина, подлежащего призыву на военную службу (приписное свидетельство) или военный билет.
							 2. Оформить регистрацию по месту пребывания (иногородним студентам, которым предоставлено место в общежитии Университета).
							 3. Прибыть в районный военный комиссариат по месту регистрации (пребывания) и представить следующие документы:
							 • паспорт и ксерокопии 2, 3 и 5 листов паспорта;
							 • свидетельство о регистрации по месту пребывания (при наличии) и его ксерокопию;
							 • удостоверение гражданина, подлежащего призыву на военную службу (приписное свидетельство) или военный билет;
							 • справку (Приложение 2 к Перечню (п. 3) из военно-учетного стола университета (только для граждан, подлежащих призыву);
							 • два конверта «По России» с марками (купить на почте).
							 4. Представить в военно-учетный стол университета (комн. 2329) удостоверение или военный билет с отметкой о постановке на воинский учет для занесения информации в базу данных.
							       
							""");

			Handbook activities = new Handbook("Внеучебная деятельность","В главное меню",
					"""
							Штаб студенческих отрядов
							https://vk.com/co_leti
							Студенческие отряды ЛЭТИ — это возможность официально устроиться на работу на каникулы, попробовать себя в разных профессиях, побывать в других городах России (и не только), завести друзей, развить в себе творческие и лидерские способности. Однажды съездив в студенческий отряд, вы получаете огромный жизненный опыт, который обязательно поможет вам в будущем.
							 
							Творческие коллективы (Арт-центр)
							https://vk.com/art_centr_leti
							Творческие коллективы, он же АРТ-ЦЕНТР - это  объединение всех музыкальных, театральных, интеллектуальных клубов. С помощью их сайта можно найти клуб подходящий именно тебе. АРТ-ЦЕНТР поможет тебе раскрыться как творческая личность, найти новых друзей, участвовать в различных конкурсах и занять свой досуг.	
														
							Студенческий совет
							https://vk.com/stud_sovet_leti
							Студсовет - это относительно молодое объединение, которые на ряду с профсоюзом занимаются социальной жизнью студентов. Каждый староста входит в студсовет автоматически, как только становится старостой. Но кроме этого, в организацию может войти любой студент. От членов выбирается по одному, который будет представлять соответствующий курс, а из них выбирается глава. Студсовет также представляет интересы студента во время комиссии по разным его вопросам.
							     
							Кураторство
							https://vk.com/studkuratorstvo  
							Кураторство - объединение активных и инициативных студентов-старшекурсников, которые помогают первокурсникам адаптироваться к жизни в вузе. В обязанности куратора входит: оповещение первокурсников о важной информации, проведение собраний, направленных на сплочение группы и помощь в урегулировании сложных ситуаций, связанных с учебой. Также кураторство занимается организацией таких вузовских мероприятий как бал, куратор-квест и тайный санта.
							       
							""");
			Handbook links = new Handbook("Интернет-ресурсы","В главное меню",
					"""
							Важные ссылки:
							
							Мудл: https://vec.etu.ru/moodle/
							Личный кабинет: https://lk.etu.ru/
							
							Активные интернет-ресурсы ЛЭТИ:
							
							Официальный сайт СПбГЭТУ «ЛЭТИ»
							https://etu.ru/
													
							Официальная страница ВКонтакте Санкт-Петербургского государственного электротехнического университета «ЛЭТИ» им. В.И. Ульянова (Ленина) (СПбГЭТУ)
							https://vk.com/spbsetu
							
							Кафедра математического обеспечения и применения ЭВМ в СПбГЭТУ "ЛЭТИ".
							https://vk.com/moevm_leti
												
							Метод ЛЭТИ
							Официальный канал Дзен Санкт-Петербургского государственного электротехнического университета «ЛЭТИ».
							https://dzen.ru/spbetu
							
							Официальная страница Одноклассники Санкт-Петербургского государственного электротехнического университета «ЛЭТИ» им. В.И. Ульянова (Ленина) (СПбГЭТУ)
							https://ok.ru/group/68758681288764
							
														
							LETI Today
							Главный телеграм-канал Санкт-Петербургского государственного электротехнического университета «ЛЭТИ».
							https://t.me/LETIToday
							 
							""");
			handbookService.save(health1);
			handbookService.save(health2);
			handbookService.save(health3);

			handbookService.save(education1);
			handbookService.save(education2);
			handbookService.save(education3);
			handbookService.save(education4);
			handbookService.save(education5);
			handbookService.save(education6);
			handbookService.save(education7);

			handbookService.save(places1);
			handbookService.save(places2);
			handbookService.save(places3);
			handbookService.save(places4);
			handbookService.save(places5);
			handbookService.save(places6);

			handbookService.save(university1);
			handbookService.save(university2);
			handbookService.save(university3);
			handbookService.save(university4);
			handbookService.save(university5);

			handbookService.save(military);
			handbookService.save(activities);
			handbookService.save(links);

		};
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
