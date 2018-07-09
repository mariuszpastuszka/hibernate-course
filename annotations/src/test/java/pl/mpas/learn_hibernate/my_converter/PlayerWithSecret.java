package pl.mpas.learn_hibernate.my_converter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class PlayerWithSecret {
    @Id
    @GeneratedValue
    private Long id;

    private String tShirtNumber;

    @Convert(converter = SecretConverter.class)
    private Secret secret;

    public PlayerWithSecret() {
    }

    public PlayerWithSecret(String tShirtNumber, Secret secret) {
        this.tShirtNumber = tShirtNumber;
        this.secret = secret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettShirtNumber() {
        return tShirtNumber;
    }

    public void settShirtNumber(String tShirtNumber) {
        this.tShirtNumber = tShirtNumber;
    }

    public Secret getSecret() {
        return secret;
    }

    public void setSecret(Secret secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "PlayerWithSecret{" +
                "id=" + id +
                ", tShirtNumber='" + tShirtNumber + '\'' +
                ", secret=" + secret +
                '}';
    }
}
