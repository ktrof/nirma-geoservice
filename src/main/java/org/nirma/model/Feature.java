package org.nirma.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * GeoJSon &#x27;Feature&#x27; object
 */
@Schema(description = "GeoJSon 'Feature' object")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-09T07:56:43.078Z[GMT]")

public class Feature extends GeoJsonObject  {
  @JsonProperty("geometry")
  private Object geometry = null;

  @JsonProperty("properties")
  private Map<String, Object> properties = new HashMap<String, Object>();

  public Feature geometry(Object geometry) {
    this.geometry = geometry;
    return this;
  }

  /**
   * Get geometry
   * @return geometry
   **/
  @Schema(required = true, description = "")
  @NotNull
  public Object getGeometry() {
    return geometry;
  }

  public void setGeometry(Object geometry) {
    this.geometry = geometry;
  }

  public Feature properties(Map<String, Object> properties) {
    this.properties = properties;
    return this;
  }

  /**
   * Get properties
   * @return properties
   **/
  @Schema(required = true, description = "")
  @NotNull
  public Object getProperties() {
    return properties;
  }

  @SuppressWarnings("unchecked")
  public <T> T getProperty(String key) {
    return (T)properties.get(key);
  }

  public void setProperty(String key, Object value) {
    properties.put(key, value);
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Feature feature = (Feature) o;
    return Objects.equals(this.geometry, feature.geometry) &&
        Objects.equals(this.properties, feature.properties) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geometry, properties, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Feature {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    geometry: ").append(toIndentedString(geometry)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
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
