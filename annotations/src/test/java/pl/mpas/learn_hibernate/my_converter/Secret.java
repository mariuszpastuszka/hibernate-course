package pl.mpas.learn_hibernate.my_converter;

public class Secret {

    private String secret;

    public Secret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "Secret{" +
                "secret='" + secret + '\'' +
                '}';
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
