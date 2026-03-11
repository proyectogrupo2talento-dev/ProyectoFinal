// package com.artesanias.service;

// import com.artesanias.model.WorkOrder;
// import com.artesanias.repository.WorkOrderRepository;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class WorkOrderService {
//     private final WorkOrderRepository workOrderRepository;

//     public WorkOrderService(WorkOrderRepository workOrderRepository) {
//         this.workOrderRepository = workOrderRepository;
//     }

//     public WorkOrder createWorkOrder(WorkOrder workOrder) {
//         workOrder.setStatus("PENDING");
//         workOrder.setCreatedAt(java.time.LocalDateTime.now());
//         return workOrderRepository.save(workOrder);
//     }

//     public List<WorkOrder> getAllWorkOrders() {
//         return workOrderRepository.findAll();
//     }

//     public WorkOrder actualizaWorkOrder(WorkOrder workOrder) {
//         return workOrderRepository.save(workOrder);
//     }

//     public WorkOrder eliminarWorkOrder(Long id){
//         workOrderRepository.deleteById(id);
//         return null;
//     }

// }