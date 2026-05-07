
package com.example.proxmoxautomation.service;

import com.example.proxmoxautomation.model.VirtualMachine;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VmService {

	private final Map<Integer, VirtualMachine> vmDatabase = new HashMap<>();

	public VmService() {

		vmDatabase.put(
				101,
				new VirtualMachine(
						101,
						"Ubuntu Server",
						"STOPPED"
				)
		);

		vmDatabase.put(
				102,
				new VirtualMachine(
						102,
						"Windows Server",
						"RUNNING"
				)
		);
	}

	public String startVm(int id) {

		VirtualMachine vm = vmDatabase.get(id);

		if (vm == null) {
			return "VM Not Found";
		}

		if (vm.getStatus().equals("RUNNING")) {
			return "VM Already Running";
		}

		vm.setStatus("RUNNING");

		return vm.getName() + " Started Successfully";
	}

	public String stopVm(int id) {

		VirtualMachine vm = vmDatabase.get(id);

		if (vm == null) {
			return "VM Not Found";
		}

		if (vm.getStatus().equals("STOPPED")) {
			return "VM Already Stopped";
		}

		vm.setStatus("STOPPED");

		return vm.getName() + " Stopped Successfully";
	}

	public String vmStatus(int id) {

		VirtualMachine vm = vmDatabase.get(id);

		if (vm == null) {
			return "VM Not Found";
		}

		return "VM Name: " +
				vm.getName() +
				" | Status: " +
				vm.getStatus();
	}

	public Collection<VirtualMachine> getAllVms() {
		return vmDatabase.values();
	}


	@Scheduled(fixedRate = 10000)
	public void automaticVmCheck() {

		System.out.println("\n--=-----");
		System.out.println("Automatic VM Check Running...");
		System.out.println("-------------");

		VirtualMachine vm = vmDatabase.get(102);

		if (vm != null &&
				vm.getStatus().equals("RUNNING")) {

			System.out.println(stopVm(102));

		} else {

			System.out.println("VM Already Stopped");
		}

		System.out.println(vmStatus(102));
	}
}