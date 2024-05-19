package com.capgemini.wsb.persistence.entity;

import com.capgemini.wsb.persistence.enums.Specialization;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	@ManyToOne(fetch = FetchType.LAZY) // Relacja dwukierunkowa, gdzie doktor zna swoje wizyty, a wizyty mają przypisanego doktora
	@JoinColumn(name = "DOCTOR_ID")
	private DoctorEntity doctorEntity;

	@ManyToOne(fetch = FetchType.LAZY) // Relacja dwukierunkowa, gdzie pacjent zna swoje wizyty, a wizyty mają przypisanego pacjenta
	@JoinColumn(name = "PATIENT_ID")
	private PatientEntity patientEntity;

	@ManyToMany( // Relacja jednokierynkowa many to many, gdzie możemy połączyć wiele medical treatment do wielu różnych wizyt
			cascade = CascadeType.ALL, // default: empty
			fetch = FetchType.LAZY // default: LAZY
	)
	@JoinTable(
			name = "MEDICALTREATMENTS_TO_VISITS",
			joinColumns = @JoinColumn(name = "MEDICAL_TREATMENT_ID"), inverseJoinColumns = @JoinColumn(name = "VISIT_ID")
	)
	private Collection<VisitEntity> visitEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public DoctorEntity getDoctorEntity() {
		return doctorEntity;
	}

	public void setDoctorEntity(DoctorEntity doctorEntity) {
		this.doctorEntity = doctorEntity;
	}

	public PatientEntity getPatientEntity() {
		return patientEntity;
	}

	public void setPatientEntity(PatientEntity patientEntity) {
		this.patientEntity = patientEntity;
	}

	public Collection<VisitEntity> getVisitEntities() {
		return visitEntities;
	}

	public void setVisitEntities(Collection<VisitEntity> visitEntities) {
		this.visitEntities = visitEntities;
	}
}
