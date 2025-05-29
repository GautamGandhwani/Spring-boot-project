package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.DoctorDTO;
import com.rays.dto.UserDTO;
import com.rays.form.DoctorForm;
import com.rays.form.UserForm;
import com.rays.service.DoctorService;

@RestController
@RequestMapping(value = "Doctor")
public class DoctorCtl extends BaseCtl {

	@Autowired
	public DoctorService doctorService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid DoctorForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		DoctorDTO dto = (DoctorDTO) form.getDto();

		if (dto.getId() != null && dto.getId() > 0) {
			doctorService.update(dto);
			res.addData(dto.getId());
			res.addMessage("Data Updated Successfully...!!!");
		} else {
			long pk = doctorService.add(dto);
			res.addData(pk);
			res.addMessage("Data Added Successfully...!!!");
		}
		return res;
	}
	
	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		DoctorDTO dto = doctorService.findById(id);

		res.addData(dto);

		return res;
	}
	
	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			doctorService.delete(id);
		}
		res.addMessage("Data Deleted Successfully...!!!");

		return res;
	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody DoctorForm form, @PathVariable int pageNo) {
		
		ORSResponse res = new ORSResponse();

		DoctorDTO dto = (DoctorDTO) form.getDto();
		dto.setName(form.getName());

		List list = doctorService.search(dto, pageNo, 5);

		if (list.size() == 0) {
			res.addMessage("Result not found...!!!");
		} else {
			res.addData(list);
		}
		return res;
	}
}