package org.example.jdbcFlyway.client;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientEntity {
    private long ID;
    private  String name;
    private long project_count;
}
