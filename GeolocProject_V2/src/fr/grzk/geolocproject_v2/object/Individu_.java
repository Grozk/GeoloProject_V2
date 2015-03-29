package fr.grzk.geolocproject_v2.object;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "telephone", "idIndividuGenere", "dateInscription",
		"isDesactive" })
public class Individu_ {

	@JsonProperty("id")
	private String id;
	@JsonProperty("telephone")
	private String telephone;
	@JsonProperty("idIndividuGenere")
	private String idIndividuGenere;
	@JsonProperty("dateInscription")
	private String dateInscription;
	@JsonProperty("isDesactive")
	private Boolean isDesactive;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("telephone")
	public String getTelephone() {
		return telephone;
	}

	@JsonProperty("telephone")
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@JsonProperty("idIndividuGenere")
	public String getIdIndividuGenere() {
		return idIndividuGenere;
	}

	@JsonProperty("idIndividuGenere")
	public void setIdIndividuGenere(String idIndividuGenere) {
		this.idIndividuGenere = idIndividuGenere;
	}

	@JsonProperty("dateInscription")
	public String getDateInscription() {
		return dateInscription;
	}

	@JsonProperty("dateInscription")
	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}

	@JsonProperty("isDesactive")
	public Boolean getIsDesactive() {
		return isDesactive;
	}

	@JsonProperty("isDesactive")
	public void setIsDesactive(Boolean isDesactive) {
		this.isDesactive = isDesactive;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
