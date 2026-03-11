// package com.artesanias.controller;

// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.artesanias.model.WorkOrder;
// import com.artesanias.service.WorkOrderService;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PathVariable;



// @RestController
// @RequestMapping("/api/work-orders")
// public class WorkOrderController {
//     private final WorkOrderService workOrderService;

//     public WorkOrderController(WorkOrderService workOrderService) {
//         this.workOrderService = workOrderService;
//     }

//     @PostMapping
//     public ResponseEntity<WorkOrder> create(@RequestBody WorkOrder workOrder) {
//         return ResponseEntity.ok(workOrderService.createWorkOrder(workOrder));
//     }

//     @GetMapping
//     public ResponseEntity<List<WorkOrder>> getAll() {
//         return ResponseEntity.ok(workOrderService.getAllWorkOrders());
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<WorkOrder> update(@PathVariable int id, @RequestBody WorkOrder workOrder) {
//         workOrder.setId(id);
//         return ResponseEntity.ok(workOrderService.actualizaWorkOrder(workOrder));
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> delete(@PathVariable Long id) {
//         workOrderService.eliminarWorkOrder(id);
//         return ResponseEntity.noContent().build();
//     }
    

// }
