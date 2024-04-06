package com.TgBotMOEVM.DTO;

import lombok.*;

/**
 * This class represents data package for content of Data parameter of an inlineButton
 * Be mindful that the size of all the data with spacing symbol must not exceed 64 bytes!
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InlineButtonDTO {

    String command;

    String data;
}
