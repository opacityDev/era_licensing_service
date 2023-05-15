package dev.opacity.auth.lib;

import java.security.SecureRandom;
import java.util.Base64;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Column;

@Entity
public class lkey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private int owner;
    
    @Column
    private String content;
    
    @Column
    private int product;

    @Column
    private String domainName;

    public lkey() {  }

    public lkey(int owner,int product) {
        this.setOwner(owner);
        this.setContent(generateLicenseKey());
        this.setProduct(product);
    }

    public static String generateLicenseKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        String base64 = Base64.getEncoder().encodeToString(bytes);
        String licenseKey = base64.replaceAll("[^a-zA-Z0-9]", "").substring(0, 25);
        return licenseKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public int getProduct() {
        return product;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    } 

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", owner_id='" + owner + '\'' +
                ", content='" + content + '\'' +
                ", product_id='" + product + '\'' +
                '}';
    }
    
}
