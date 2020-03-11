/**
 * 
 */
package com.concretesolutions.desafio.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gedson
 *
 */
@Entity
@Table(name = "phone")
@Getter
@Setter
@EqualsAndHashCode
public class Phone {

	@Id
	@Column(name = "phone_number")
	private Long number;
	
	private Integer ddd;
}
