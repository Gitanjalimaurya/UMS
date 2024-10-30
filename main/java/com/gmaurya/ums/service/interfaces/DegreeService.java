package com.gmaurya.ums.service.interfaces;

import com.gmaurya.ums.entity.Degree;
import java.util.List;

public interface DegreeService {
    List<Degree> getAllDegrees();
    void saveDegree(Degree degree);
    String deleteDegree(String degreeId);  // Now accepts string

    Degree getDegreeByName(String degreeName);
}
