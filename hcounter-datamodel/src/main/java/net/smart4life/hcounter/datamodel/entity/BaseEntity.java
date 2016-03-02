package net.smart4life.hcounter.datamodel.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Created by roman on 21.09.2015.
 */
@MappedSuperclass
public class BaseEntity implements Identifiable<Long>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column
    private Long version;
    
    @Column
    @CreationTimestamp
    private Date creaDate;
    
    @Column
    @UpdateTimestamp
    private Date modDate;
    
    @Column
    @NotNull
    private String creaUser;
    
    @Column String modUser;

    @Override
    public Long getId() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

	public Date getCreaDate() {
		return creaDate;
	}

	public void setCreaDate(Date creaDate) {
		this.creaDate = creaDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String getCreaUser() {
		return creaUser;
	}

	public void setCreaUser(String creaUser) {
		this.creaUser = creaUser;
	}

	public String getModUser() {
		return modUser;
	}

	public void setModUser(String modUser) {
		this.modUser = modUser;
	}
    
}
