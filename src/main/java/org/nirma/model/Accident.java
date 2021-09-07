package org.nirma.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

@Document(collection = "accidents")
@Getter
@Setter
public class Accident extends Feature {

    @Id
    @JsonProperty("id")
    private String id = null;

    public Accident() {}

    public Accident(final Feature feature) {
        try {
            PropertyUtils.copyProperties(this, feature);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident feature = (Accident) o;
        return Objects.equals(this.id, feature.id) &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Accident {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
