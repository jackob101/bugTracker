package com.trix.bugtracker.DTO;

import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.enums.Priority;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class IssuePojo {


	@NotNull
	private String description;

	@NotNull
	private String title;

	@NotNull
	private Long projectId;

	private String projectName;

	private LocalDateTime openedTime;
	private LocalDateTime closedTime;

	@NotNull
	private Priority priority;

	@NotNull
	private Long createdBy;

	@Override
	public String toString() {
		return "IssuePojo [projectName=" + projectName + "]";
	}

	public IssuePojo(Issue issue) {
		this.description = issue.getDescription();
		this.openedTime = issue.getOpenedTime();
		this.closedTime = issue.getClosedTime();
		this.priority = issue.getPriority();
		if (issue.getProject() != null) {
			this.projectId = issue.getProject().getId();
			this.projectName = issue.getProject().getName();
		} else {
			this.projectId = 1L;
			this.projectName = "";
		}
	}

}
