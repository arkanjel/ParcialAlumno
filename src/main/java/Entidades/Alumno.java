package Entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Alumno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
    @Id
    private int id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Apellido" )
    private String apellido;
    @Column(name ="Mail" )
    private String mail;
    @Column(name ="Creacion alumno" )
    private Date createAl;
}
