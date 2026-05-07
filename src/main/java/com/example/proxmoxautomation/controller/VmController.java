
package com.example.proxmoxautomation.controller;

import com.example.proxmoxautomation.model.VirtualMachine;
import com.example.proxmoxautomation.service.VmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/vm")
public class VmController {

	@Autowired
	private VmService vmService;


	@GetMapping("/start/{id}")
	public String startVm(@PathVariable int id) {
		return vmService.startVm(id);
	}


	@GetMapping("/stop/{id}")
	public String stopVm(@PathVariable int id) {
		return vmService.stopVm(id);
	}


	@GetMapping("/status/{id}")
	public String vmStatus(@PathVariable int id) {
		return vmService.vmStatus(id);
	}


	@GetMapping("/all")
	public Collection<VirtualMachine> allVms() {
		return vmService.getAllVms();
	}
}