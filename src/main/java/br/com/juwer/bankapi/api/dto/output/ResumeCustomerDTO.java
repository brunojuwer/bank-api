package br.com.juwer.bankapi.api.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeCustomerDTO {
    private Long id;
    private String fullName;
    private String email;
}
