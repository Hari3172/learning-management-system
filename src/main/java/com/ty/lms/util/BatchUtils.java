package com.ty.lms.util;

import java.time.Year;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;
import com.ty.lms.dto.BatchDTO;
import com.ty.lms.dto.DashboardDTO;
import com.ty.lms.entity.Batch;
import com.ty.lms.entity.enums.Gender;
import com.ty.lms.entity.enums.Performance;

public class BatchUtils {

	public static Batch dtoToEntity(BatchDTO batchDTO) {
		Batch batch = Batch.builder().build();
		BeanUtils.copyProperties(batchDTO, batch);
		return batch;
	}

	public static DashboardDTO entityToDTO(List<HashMap<String, Object>> list, Performance batchPerformance) {
		DashboardDTO dashboardDTO = DashboardDTO.builder().build();

		List<Gender> genders = Lists.newArrayList();
		List<Year> yearOfPassing = Lists.newArrayList();
		List<Integer> experience = Lists.newArrayList();
		List<String> employeeDegrees = Lists.newArrayList();

		for (HashMap<String, Object> hashMap : list) {
			genders.add((Gender) hashMap.get("Gender"));
			yearOfPassing.add((Year) hashMap.get("Year"));
			employeeDegrees.add((String) hashMap.get("degree"));
			experience.add((Integer) hashMap.get("Experience"));
		}

		dashboardDTO.setGenders(genders);
		dashboardDTO.setExperience(experience);
		dashboardDTO.setYearOfPassing(yearOfPassing);
		dashboardDTO.setEmployeeDegrees(employeeDegrees);
		dashboardDTO.setBatchPerformance(batchPerformance);

		return dashboardDTO;
	}

	public static BatchDTO entityToDTO(Batch batch) {
		BatchDTO batchDTO = BatchDTO.builder().build();
		BeanUtils.copyProperties(batch, batchDTO);
		batchDTO.setTechnologies(batch.getTechnologies());
		return batchDTO;
	}

}
