package com.prueba.tecnica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "empleado")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"Registro", "updatedAt"},allowGetters = true)
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 20)
    @Column(nullable = false)
    private String primer_apellido;

    @Length(max = 20)
    @Column(nullable = false)
    private String segundo_apellido;

    @Length(max = 20)
    @Column(nullable = false)
    private String primer_nombre;

    @Length(max = 50)
    @Column(nullable = false)
    private String otro_nombre;

    @ManyToOne
    @JoinColumn(columnDefinition = "pais_id", nullable = false)
    private Pais pais;

    @OneToOne
    @JoinColumn(columnDefinition = "tipo_id", nullable = false)
    private Identificacion identificacion;

    @Length(max = 20)
    @Column(nullable = false)
    private String nid;

    @Length(max = 300)
    @Column(nullable = false)
    private String email;

    @Length(max = 10)
    @Column(nullable = false)
    private String fecha_ingreso;

    @OneToOne
    @JoinColumn(columnDefinition = "area_id", nullable = false)
    private Area area;

    @Column(nullable = false)
    private Boolean estado;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date Registro;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

}
