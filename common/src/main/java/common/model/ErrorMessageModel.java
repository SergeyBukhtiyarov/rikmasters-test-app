package common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessageModel {
    private String message;
    private String details;
}
