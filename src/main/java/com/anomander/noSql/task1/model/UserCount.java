package com.anomander.noSql.task1.model;

import lombok.*;
import lombok.experimental.Accessors;

@ToString
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserCount {

    private int id;
    private int count;

}
