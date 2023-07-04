package br.com.juwer.bankapi.api.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanTypeDTO {
    private Long id;
    private String name;
    private String description;
}
