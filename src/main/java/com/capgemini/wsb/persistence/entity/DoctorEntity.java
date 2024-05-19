package com.capgemini.wsb.persistence.entity;

import com.capgemini.wsb.persistence.enums.Specialization;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "DOCTOR")
public class DoctorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String telephoneNumber;

	private String email;

	@Column(nullable = false)
	private String doctorNumber;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	@OneToMany(mappedBy = "doctorEntity", cascade = CascadeType.ALL) // Relacja dwukierunkowa, gdzie lekarz zna swoje wizyty, a wizyty mają przypisanego lekarza
	private Collection<VisitEntity> visitEntities;

	@ManyToOne(fetch = FetchType.LAZY) // Relacja jednokierunkowa z Doctor do Address, ponieważ adres nie musi znać informacji o lekarzach
	@JoinColumn(name = "ADDRESS_ID")
	private AddressEntity addressEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(String doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public Collection<VisitEntity> getVisitEntities() {
		return visitEntities;
	}

	public void setVisitEntities(Collection<VisitEntity> visitEntities) {
		this.visitEntities = visitEntities;
	}

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}
}
