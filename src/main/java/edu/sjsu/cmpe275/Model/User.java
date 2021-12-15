package edu.sjsu.cmpe275.Model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@XmlRootElement
@Table(name = "user")
@Entity
public class User implements UserDetails {

	public User() {
		super();
	}
	
	

	public User(String firstName, String lastName, AppUserRole appUserRole, Date dateOfBirth, String gender,
			Boolean verified, String role, String password, String email, String address, String city, String state, int zipcode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.appUserRole = appUserRole;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.verified = verified;
		this.role = role;
		this.password = password;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}



	/*
	 * public User(String firstName, String lastName, AppUserRole appUserRole,
	 * String password, String email) { super(); this.firstName = firstName;
	 * this.lastName = lastName; this.appUserRole = appUserRole; this.password =
	 * password; this.email = email; }
	 */

	/*
	 * public User(String firstName, String lastName, AppUserRole appUserRole, Long
	 * id, Address address, List<UserVaccination> vaccinationHistory,
	 * List<Appointment> appointments, Boolean locked, Boolean enabled, String
	 * password, String email) { super(); this.firstName = firstName; this.lastName
	 * = lastName; this.appUserRole = appUserRole; this.id = id; this.address =
	 * address; this.vaccinationHistory = vaccinationHistory; this.appointments =
	 * appointments; this.locked = locked; this.enabled = enabled; this.password =
	 * password; this.email = email; }
	 */

	@Column(name = "first_name",nullable = false)
	private String firstName;

	@Column(name = "last_name",nullable = false)
	private String lastName;

	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MRN")
	@SequenceGenerator(name = "MRN", sequenceName = "MRN", initialValue = 100, allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

	
	  @Column(name = "middle_name") 
	  private String middleName;
	  
	  @Column(name = "date_of_birth", nullable = false) 
	  private Date dateOfBirth;
	  
	  @Column(name = "gender", nullable = false) 
	  private String gender;
	  
	  @Column(name = "verified", nullable = false) 
	  private Boolean verified = false;
	 

	
	  @Column(name = "role", nullable = false)
	  private String role;
	 
	  @Column(name = "address" , columnDefinition = "varchar(15) default hey")
	  private String address;
	  
	  @Column(name = "city", columnDefinition = "varchar(15) default San Jose")
	  private String city;
	  
	  @Column(name = "state", columnDefinition = "varchar(15) default CA")
	  private String state;
	  
	  @Column(name = "zipcode", columnDefinition = "integer default 95112")
	  private int zipcode;


	  
		/*
		 * @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
		 * 
		 * @JoinColumn(name = "address_ID") private Address address;
		 */
	 

	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public int getZipcode() {
		return zipcode;
	}



	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}



	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserVaccination> vaccinationHistory;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments;

	private Boolean locked = false;

	private Boolean enabled = false;

	private String password;

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<UserVaccination> getVaccinationHistory() {
		return vaccinationHistory;
	}

	public void setVaccinationHistory(List<UserVaccination> vaccinationHistory) {
		this.vaccinationHistory = vaccinationHistory;
	}

	/*
	 * public Address getAddress() { return address; }
	 * 
	 * public void setAddress(Address address) { this.address = address; }
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public AppUserRole getAppUserRole() {
		return appUserRole;
	}

	public void setAppUserRole(AppUserRole appUserRole) {
		this.appUserRole = appUserRole;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

}