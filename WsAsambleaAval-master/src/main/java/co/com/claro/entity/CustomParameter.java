package co.com.claro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CUSTOM_PARAMETERS")

public class CustomParameter {
	
	@Id
    @SequenceGenerator(name = "parameters_id_generator", sequenceName = "parameters_id_seq")
    @GeneratedValue(generator = "parameters_id_generator")
    @Getter @Setter
    private Long id;

    @Column(name = "PARAMETER", length = 500)
    @Getter @Setter
    private String parameter;

    @Column(name = "DESC_PARAMETER", length = 500)
    @Getter @Setter
    private String descParameter;
    
}
