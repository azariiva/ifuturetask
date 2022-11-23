package ru.spbifuture.account.server.businesslogic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`account`")
public class Account {

    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`balance`")
    private Long amount;
}
