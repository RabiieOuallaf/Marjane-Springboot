package ma.yc.marjane.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ClientModel")
@Table(name = "client")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Basic
    @Column(name = "fullname")
    private String fullName;

    @Basic
    @Column(name =  "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;


}
