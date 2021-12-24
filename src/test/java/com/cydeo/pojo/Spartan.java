package com.cydeo.pojo;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Data this is combination of above and few more dependency
public class Spartan {

    private String name;
    private String gender;
    private long phone;

}
