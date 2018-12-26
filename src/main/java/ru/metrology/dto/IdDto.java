package ru.metrology.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class IdDto implements Serializable {

    @NotNull
    @Getter @Setter
    private Long id;
}
