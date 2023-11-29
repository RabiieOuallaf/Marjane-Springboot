package ma.yc.marjane.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "CashierAgentModel")
@Table(name = "cashier_agent")
@Data
public class CashierAgentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "fullname")
    private String fullname;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "role")
    private String role;

}
