package com.crmpetproject.crmpetproject.servives.interfaces;

import com.crmpetproject.crmpetproject.models.ProjectProperties;

public interface ProjectPropertiesService extends CommonService<ProjectProperties> {

    ProjectProperties get();

    ProjectProperties getOrCreate();

    ProjectProperties saveAndFlash(ProjectProperties entity);
}
