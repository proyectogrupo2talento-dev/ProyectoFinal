// package com.artesanias.service;

// import com.artesanias.model.WorkOrderConsumption;
// import com.artesanias.repository.WorkOrderConsumptionRepository;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class WorkOrderConsumptionService {
//     private final WorkOrderConsumptionRepository consumptionRepository;

//     public WorkOrderConsumptionService(WorkOrderConsumptionRepository consumptionRepository) {
//         this.consumptionRepository = consumptionRepository;
//     }

//     public WorkOrderConsumption registrarConsumo(WorkOrderConsumption consumption) {
//         consumption.setCreatedAt(java.time.LocalDateTime.now());
//         return consumptionRepository.save(consumption);
//     }

//     public List<WorkOrderConsumption> listarConsumos() {
//         return consumptionRepository.findAll();
//     }

//     public void eliminarConsumo(Long id) {
//         consumptionRepository.deleteById(id);
//     }
// }