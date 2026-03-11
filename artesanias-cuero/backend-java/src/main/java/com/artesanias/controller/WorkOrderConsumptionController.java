// package com.artesanias.controller;

// import com.artesanias.model.WorkOrderConsumption;
// import com.artesanias.service.WorkOrderConsumptionService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping ("/api/work-order-consumptions")
// public class WorkOrderConsumptionController {

//     private final WorkOrderConsumptionService consumptionService;

//     public WorkOrderConsumptionController(WorkOrderConsumptionService consumptionService) {
//         this.consumptionService = consumptionService;
//     }

//     @PostMapping
//     public ResponseEntity<WorkOrderConsumption> create(@RequestBody WorkOrderConsumption consumption) {
//         return ResponseEntity.ok(consumptionService.registrarConsumo(consumption));
//     }

//     @GetMapping
//     public ResponseEntity<List<WorkOrderConsumption>> getAll() {
//         return ResponseEntity.ok(consumptionService.listarConsumos());
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> delete(@PathVariable Long id) {
//         consumptionService.eliminarConsumo(id);
//         return ResponseEntity.noContent().build();
//     }

// }
