package com.example.TestSecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author karpeykin
 * @Date 21.01.2021
 */

@Data
@AllArgsConstructor
public class Developer {
    private Long id;
    private String firstname;
    private String lastname;

}
