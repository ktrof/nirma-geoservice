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
 * GeoJSon &#x27;FeatureCollection&#x27; object
 */
@Schema(description = "GeoJSon 'FeatureCollection' object")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-09T07:56:43.078Z[GMT]")


public class FeatureCollection extends GeoJsonObject  {
  @JsonProperty("features")
  @Valid
  private List<Feature> features = new ArrayList<Feature>();

  public FeatureCollection() {
    setType(TypeEnum.FEATURECOLLECTION);
  }

  public FeatureCollection(@Valid List<Feature> features) {
    this();
    this.features = features;
  }

  public FeatureCollection features(List<Feature> features) {
    this.features = features;
    return this;
  }

  public FeatureCollection addFeaturesItem(Feature featuresItem) {
    this.features.add(featuresItem);
    return this;
  }

  /**
   * Get features
   * @return features
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<Feature> getFeatures() {
    return features;
  }

  public void setFeatures(List<Feature> features) {
    this.features = features;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeatureCollection featureCollection = (FeatureCollection) o;
    return Objects.equals(this.features, featureCollection.features) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(features, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeatureCollection {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    features: ").append(toIndentedString(features)).append("\n");
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
