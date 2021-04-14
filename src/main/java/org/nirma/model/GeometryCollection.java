package org.nirma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GeoJSon geometry collection GeometryCollections composed of a single part or a number of parts of a single type SHOULD be avoided when that single part or a single object of multipart type (MultiPoint, MultiLineString, or MultiPolygon) could be used instead. 
 */
@Schema(description = "GeoJSon geometry collection GeometryCollections composed of a single part or a number of parts of a single type SHOULD be avoided when that single part or a single object of multipart type (MultiPoint, MultiLineString, or MultiPolygon) could be used instead. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-09T07:56:43.078Z[GMT]")


public class GeometryCollection extends Geometry  {
  @JsonProperty("geometries")
  @Valid
  private List<GeometryElement> geometries = new ArrayList<GeometryElement>();

  public GeometryCollection geometries(List<GeometryElement> geometries) {
    this.geometries = geometries;
    return this;
  }

  public GeometryCollection addGeometriesItem(GeometryElement geometriesItem) {
    this.geometries.add(geometriesItem);
    return this;
  }

  /**
   * Get geometries
   * @return geometries
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<GeometryElement> getGeometries() {
    return geometries;
  }

  public void setGeometries(List<GeometryElement> geometries) {
    this.geometries = geometries;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeometryCollection geometryCollection = (GeometryCollection) o;
    return Objects.equals(this.geometries, geometryCollection.geometries) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geometries, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeometryCollection {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    geometries: ").append(toIndentedString(geometries)).append("\n");
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
