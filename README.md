# VM Automation Backend Service

## Project Overview

This project is a backend automation system developed using Java and Spring Boot.

The application simulates Virtual Machine (VM) lifecycle management and demonstrates how backend automation systems work in real-world infrastructure platforms.

The system allows:

* Starting Virtual Machines
* Stopping Virtual Machines
* Checking VM Status
* Viewing all VMs
* Automatic VM monitoring using Spring Scheduler

The project follows layered backend architecture using:

* Controller Layer
* Service Layer
* Model Layer

Although the project does not connect to a real Proxmox server, it simulates the same backend workflow and automation concepts.

---

# Objective of the Project

The objective of this project is to understand:

* Spring Boot backend development
* REST API development
* Layered architecture
* Backend automation concepts
* Scheduling tasks in Spring Boot
* State management using Java collections

---

# Technologies Used

| Technology       | Purpose               |
| ---------------- | --------------------- |
| Java             | Programming Language  |
| Spring Boot      | Backend Framework     |
| Spring Web       | REST API Development  |
| Spring Scheduler | Automation            |
| Maven            | Dependency Management |
| HashMap          | Simulated VM Database |

---

# Project Structure

```text
src/main/java
│
├── controller
│     └── VmController.java
│
├── service
│     └── VmService.java
│
├── model
│     └── VirtualMachine.java
│
└── ProxmoxAutomationApplication.java
```

---

# Project Flow

```text
Client Request
      ↓
Controller Layer
      ↓
Service Layer
      ↓
Virtual Machine Database (HashMap)
      ↓
VM State Updated
      ↓
Response Returned
```

---

# Explanation of Each Layer

## 1. Controller Layer

File:

```text
VmController.java
```

Responsibilities:

* Handles HTTP requests
* Creates API endpoints
* Receives user input
* Sends requests to Service Layer

Example:

```java
@GetMapping("/start/{id}")
```

---

## 2. Service Layer

File:

```text
VmService.java
```

Responsibilities:

* Contains business logic
* Starts and stops VMs
* Checks VM state
* Handles automation logic
* Performs scheduled tasks

---

## 3. Model Layer

File:

```text
VirtualMachine.java
```

Responsibilities:

* Represents Virtual Machine object
* Stores VM data

Attributes:

* id
* name
* status

---

# Virtual Machine Simulation

The project simulates virtual machines using:

```java
Map<Integer, VirtualMachine>
```

This works as a temporary in-memory database.

Example:

```text
101 -> Ubuntu Server -> STOPPED
102 -> Windows Server -> RUNNING
```

---

# API Endpoints

## 1. Get All Virtual Machines

### Endpoint

```text
GET /vm/all
```

### Example

```text
http://localhost:8081/vm/all
```

### Sample Response

```json
[
  {
    "id": 101,
    "name": "Ubuntu Server",
    "status": "STOPPED"
  },
  {
    "id": 102,
    "name": "Windows Server",
    "status": "RUNNING"
  }
]
```

---

## 2. Start Virtual Machine

### Endpoint

```text
GET /vm/start/{id}
```

### Example

```text
http://localhost:8081/vm/start/101
```

### Expected Output

```text
Ubuntu Server Started Successfully
```

---

## 3. Stop Virtual Machine

### Endpoint

```text
GET /vm/stop/{id}
```

### Example

```text
http://localhost:8081/vm/stop/101
```

### Expected Output

```text
Ubuntu Server Stopped Successfully
```

---

## 4. Check VM Status

### Endpoint

```text
GET /vm/status/{id}
```

### Example

```text
http://localhost:8081/vm/status/101
```

### Expected Output

```text
VM Name: Ubuntu Server | Status: RUNNING
```

---

# Automation Feature

The project uses Spring Scheduler for automation.

Annotation used:

```java
@Scheduled(fixedRate = 10000)
```

Meaning:

```text
Run method automatically every 10 seconds
```

---

# Automation Flow

```text
Spring Scheduler
       ↓
automaticVmCheck()
       ↓
Fetch VM 102
       ↓
Check VM Status
       ↓
If RUNNING → Stop VM
       ↓
Print Updated Status
```

---

# Scheduled Automation Method

```java
@Scheduled(fixedRate = 10000)
public void automaticVmCheck() {

    System.out.println("Automatic VM Check Running...");

    VirtualMachine vm = vmDatabase.get(102);

    if (vm != null &&
            vm.getStatus().equals("RUNNING")) {

        System.out.println(stopVm(102));

    } else {

        System.out.println("VM Already Stopped");
    }

    System.out.println(vmStatus(102));
}
```

---

# How Automation Works

Every 10 seconds:

1. Spring Boot automatically executes the method.
2. VM with ID 102 is fetched.
3. VM status is checked.
4. If VM is RUNNING:

   * VM is automatically stopped.
5. Updated VM status is printed in console.

This demonstrates backend automation without manual user interaction.

---

# Test Cases

## Test Case 1 — Get All VMs

### Input

```text
GET /vm/all
```

### Expected Result

List of all VMs should be returned.

### Status

PASS

---

## Test Case 2 — Start Existing VM

### Input

```text
GET /vm/start/101
```

### Expected Result

```text
Ubuntu Server Started Successfully
```

### Status

PASS

---

## Test Case 3 — Start Already Running VM

### Input

```text
GET /vm/start/102
```

### Expected Result

```text
VM Already Running
```

### Status

PASS

---

## Test Case 4 — Stop Existing VM

### Input

```text
GET /vm/stop/102
```

### Expected Result

```text
Windows Server Stopped Successfully
```

### Status

PASS

---

## Test Case 5 — Stop Already Stopped VM

### Input

```text
GET /vm/stop/101
```

### Expected Result

```text
VM Already Stopped
```

### Status

PASS

---

## Test Case 6 — Invalid VM ID

### Input

```text
GET /vm/status/999
```

### Expected Result

```text
VM Not Found
```

### Status

PASS

---

## Test Case 7 — Automation Check

### Condition

VM 102 status = RUNNING

### Expected Result

Scheduler automatically stops VM after scheduled interval.

### Console Output

```text
Automatic VM Check Running...
Windows Server Stopped Successfully
VM Name: Windows Server | Status: STOPPED
```

### Status

PASS

---

# How to Run the Project

## Step 1

Clone Repository:

```bash
git clone <repository-url>
```

---

## Step 2

Open project in IntelliJ IDEA.

---

## Step 3

Run application:

```bash
mvn spring-boot:run
```

---

## Step 4

Server starts on:

```text
http://localhost:8081
```

---

# Future Improvements

Possible future enhancements:

* Real Proxmox API integration
* Database integration using MySQL
* Authentication using Spring Security
* Docker containerization
* Logging system
* Frontend dashboard
* VM resource monitoring

---

# Conclusion

This project demonstrates backend automation concepts using Spring Boot.

The system simulates Virtual Machine lifecycle management and automatic scheduling while following clean layered architecture.

The project helps in understanding:

* REST APIs
* Spring Boot architecture
* Automation scheduling
* Backend workflow
* Service-based application design
