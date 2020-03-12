/**
 * 
 */
package com.concretesolutions.desafio.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Phone model definition
 * 
 * @author gedson
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
