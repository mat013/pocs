package dk.emstar.test.spring.kafkapostgres.jpa;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

@Entity
@RevisionEntity
@Table(name = "REV_TABLE")
@SequenceGenerator(name = "GENERIC_GENERATOR", sequenceName = "SQ_REVISION_ID")
public class Revinfo implements Serializable {
    private static final long serialVersionUID = 8530213963961662300L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENERIC_GENERATOR")
    @RevisionNumber
    private int id;

    @RevisionTimestamp
    private long timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Transient
    public Date getRevisionDate() {
        return new Date( timestamp );
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( !(o instanceof org.hibernate.envers.DefaultRevisionEntity) ) {
            return false;
        }

        final Revinfo that = (Revinfo) o;
        return id == that.id
                && timestamp == that.timestamp;
    }

    @Override
    public int hashCode() {
        int result;
        result = id;
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DefaultRevisionEntity(id = " + id
                + ", revisionDate = " + DateFormat.getDateTimeInstance().format( getRevisionDate() ) + ")";
    }
}
