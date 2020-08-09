package com.minimal.todoapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String username;

	private String password;

	private String email;

	@OneToMany(mappedBy = "user")
	private List<Task> tasks;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

}
