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
 * GeoJSon geometry
 */
@Schema(description = "GeoJSon geometry")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-09T07:56:43.078Z[GMT]")


public class MultiLineString extends GeometryElement  {
  @JsonProperty("coordinates")
  @Valid
  private List<LineStringCoordinates> coordinates = new ArrayList<LineStringCoordinates>();

  public MultiLineString coordinates(List<LineStringCoordinates> coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  public MultiLineString addCoordinatesItem(LineStringCoordinates coordinatesItem) {
    this.coordinates.add(coordinatesItem);
    return this;
  }

  /**
   * Get coordinates
   * @return coordinates
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<LineStringCoordinates> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<LineStringCoordinates> coordinates) {
    this.coordinates = coordinates;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MultiLineString multiLineString = (MultiLineString) o;
    return Objects.equals(this.coordinates, multiLineString.coordinates) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordinates, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MultiLineString {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n");
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
