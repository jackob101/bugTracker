package com.trix.bugtracker.DTO;

import com.trix.bugtracker.model.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssueDTO {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1, max = 250, message = "Title must be between 1-250 chars")
    private String title;

    @NotNull
    @Size(min = 1, max = 500, message = "Description must be between 1-500 chars")
    private String description;

    private boolean closed;

    @NotNull
    private Priority priority;
    private List<Long> assignedUsersId;


}
