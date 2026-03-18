

# 🚦 Smart Traffic Simulator – Professional Edition

## 1. Introduction
**Smart Traffic Simulator** là một hệ thống mô phỏng giao thông tại một **ngã tư thông minh** được xây dựng bằng **Java (8+)**.  
Ứng dụng chạy dưới dạng **GUI-based simulation với real-time visualization** và mô phỏng cách các phương tiện giao thông di chuyển, phản ứng với tín hiệu đèn giao thông, xử lý các tình huống ưu tiên và quản lý tồn tại điểm giao lộ.

Mục tiêu chính của dự án là thực hành:

- **Object-Oriented Design (OOAD)** - Thiết kế hướng đối tượng chuẩn
- **SOLID Principles** - Các nguyên tắc thiết kế phần mềm
- **Multithreading & Synchronization** - Xử lý song song và đồng bộ hóa
- **Design Patterns** - Factory, Observer, State Pattern
- **Concurrent Collections** - Tập hợp an toàn cho luồng
- **Unit Testing** - JUnit 5 & Mockito
- **GUI Development** - Swing & Real-time Rendering

Hệ thống mô phỏng **hàng chục phương tiện cùng lúc**, điều phối tại giao lộ thông minh, tránh va chạm và ưu tiên cho xe cứu thương.

---

## 2. System Architecture

### 2.1 Workflow Diagram

```
┌─────────────────────── SMART TRAFFIC SIMULATOR ──────────────────────┐
│                                                                       │
│  ┌──────────────── USER INTERFACE (Swing GUI) ──────────────────┐   │
│  │                                                               │   │
│  │  [Control Panel]  [Status Bar]  [Speed Control] [Buttons]   │   │
│  │  ▶ START │ ⏸ PAUSE │ 🔄 RESET                              │   │
│  │                                                               │   │
│  │  ┌─────────────── SIMULATION CANVAS ──────────────────┐     │   │
│  │  │                                                     │     │   │
│  │  │  🚔 🚕 🚗                     🚨 (Priority)        │     │   │
│  │  │                  🚦 INTERSECTION                    │     │   │
│  │  │  🚙 🚐                    🚔                        │     │   │
│  │  │                                                     │     │   │
│  │  │  Real-time Vehicle Visualization                   │     │   │
│  │  │  Traffic Light Status (GREEN/YELLOW/RED)           │     │   │
│  │  │  Statistics (Time, Passed, Waiting)                │     │   │
│  │  └─────────────────────────────────────────────────────┘     │   │
│  │                                                               │   │
│  └───────────────────────────────────────────────────────────────┘   │
│                              ▲                                        │
│                              │  Updates UI                            │
│                              │                                        │
├──────────────────────────────┼────────────────────────────────────────┤
│                              │                                        │
│                    ┌─────────▼──────────┐                            │
│                    │ Simulation Engine  │                            │
│                    │ (SimulationEngine) │                            │
│                    └─────────┬──────────┘                            │
│                              │                                        │
│          ┌───────────────────┼───────────────────┐                   │
│          │                   │                   │                   │
│          ▼                   ▼                   ▼                   │
│    ┌──────────┐        ┌──────────────┐    ┌──────────────┐         │
│    │ Traffic  │        │ Intersection │    │   Vehicle    │         │
│    │ Light    │        │  Manager     │    │  Generator   │         │
│    │(Runnable)│        │              │    │              │         │
│    └────┬─────┘        └──────┬───────┘    └──────┬───────┘         │
│         │                     │                    │                 │
│         │ GREEN/YELLOW/RED    │ Coordinates       │ Car/Truck/      │
│         │ (Every 15/3/10s)    │ Entrance/Exit    │ Motorbike/       │
│         │                     │ Queuing Logic    │ Ambulance        │
│         │                     │                   │                 │
│         └─────────────┬───────┴───────────────────┘                 │
│                       │                                              │
│              ┌────────▼─────────┐                                   │
│              │  Executor Thread │                                   │
│              │  Pool (10 threads)                                   │
│              └────────┬─────────┘                                   │
│                       │                                              │
│        ┌──────┬───────┼───────┬────────────────┐                    │
│        │      │       │       │                │                    │
│        ▼      ▼       ▼       ▼                ▼                    │
│     [V1]  [V2]   [V3]   [V4]  ...        [V50]                    │
│     │      │      │      │                 │                        │
│     │      │      │      │    Concurrent    │                        │
│     │      │      │      │    Execution     │                        │
│     │      │      │      │                 │                        │
│     └──────┴──────┴──────┴─────────────────┘                        │
│              Vehicle Simulation Logic                                │
│              - Check Light Status                                   │
│              - Move Forward/Stop                                    │
│              - Collision Detection                                  │
│              - Priority Handling (Ambulance)                        │
│              - Queue Management                                     │
│              - Exception Handling                                   │
│                                                                    │
└────────────────────────────────────────────────────────────────────┘
```

### 2.2 Component Relationship

```
┌─────────────────┐               ┌──────────────────┐
│ SimulatorWindow │ (Main GUI)    │ SimulationUI     │ (Canvas)
│                 │───────────────│                  │
│ - Controls      │               │ - Draws Roads    │
│ - Status Panel  │               │ - Draws Lights   │
│ - Buttons       │               │ - Draws Vehicles │
└────────┬────────┘               │ - Statistics     │
         │                        └──────────────────┘
         │                                 ▲
         │ Starts Engine                   │
         │                          Updates UI
         ▼                                 │
┌──────────────────────────────────────────┴──────────┐
│        SimulationEngine (Main Logic)                 │
│                                                      │
│  TrafficLight ◄────────────────────► Intersection   │
│   (State)      Thread-Safe Update      (Queue)      │
│                                                      │
│  50 Vehicles (ExecutorService)                      │
│  - StandardVehicle (Car, Truck, Motorbike)          │
│  - PriorityVehicle (Ambulance)                      │
│                                                      │
└──────────────────────────────────────────────────────┘
```

---

## 3. Project Goals

Dự án được xây dựng nhằm đạt các mục tiêu sau:

### 3.1 Áp dụng Object-Oriented Design
Thiết kế hệ thống theo mô hình **OOAD chuẩn**, đảm bảo:

- Code dễ mở rộng (vd: thêm loại phương tiện mới)
- Dễ bảo trì (tách biệt trách nhiệm)
- Tái sử dụng (Factory Pattern cho Vehicle)
- Linh hoạt (State Pattern cho TrafficLight)

### 3.2 Tuân thủ SOLID Principles

| Principle | Áp dụng |
|-----------|--------|
| **SRP** (Single Responsibility) | TrafficLight chỉ quản lý trạng thái đèn; Intersection quản lý queue |
| **OCP** (Open/Closed) | Có thể thêm loại xe mới (thừa kế Vehicle) mà không sửa logic lõi |
| **LSP** (Liskov Substitution) | StandardVehicle, PriorityVehicle thay thế được lớp cha Vehicle |
| **ISP** (Interface Segregation) | Observer, Subject các interface nhỏ, rõ ràng |
| **DIP** (Dependency Inversion) | Module phụ thuộc abstraction (interfaces), không concrete class |

### 3.3 Multithreading & Concurrency

Hệ thống mô phỏng **nhiều phương tiện chạy song song** với:

- **Thread**: Cho TrafficLight (state change) và UI update
- **ExecutorService**: Fixed pool (10 threads) cho vehicle simulation
- **AtomicReference**: Thread-safe state management cho TrafficLight
- **ConcurrentHashMap**: Lưu trữ vehicles an toàn
- **Synchronization**: Queue management tại Intersection

### 3.4 Design Patterns

| Pattern | Vị trí | Mục đích |
|---------|--------|---------|
| **Factory Pattern** | `VehicleFactory.java` | Tạo Vehicle objects |
| **Observer Pattern** | `Observer.java`, `Subject.java` | Thông báo state change |
| **State Pattern** | `GreenState.java`, `RedState.java`, `YellowState.java` | Quản lý TrafficLight states |
| **Singleton** | TrafficLight | Bảo đảm một instance duy nhất |

---

## 4. Execution Flow

### 4.1 Sequence Diagram

```
User                 SimulatorWindow          SimulationUI        SimulationEngine
 │                         │                       │                    │
 │─ Click START ────────────│                       │                    │
 │                          │                       │                    │
 │                          │─ Create TrafficLight─ │                    │
 │                          │                       │                    │
 │                          │─ Create Intersection ─│                    │
 │                          │                       │                    │
 │                          │─ Start TrafficLight Thread ─────────────── │
 │                          │   (state: RED→GREEN→YELLOW→RED)            │
 │                          │                       │                    │
 │                          │─ Start Generator (50 Vehicles) ──────────┐ │
 │                          │                       │                  │ │
 │                          │                       │    [Every 500ms] │ │
 │                          │                       │    Generate 1 │ │
 │                          │                       │    Vehicle ◄─┘ │
 │                          │                       │                  │
 │                          │─ Start UI Update (every 50ms) ┐          │
 │                          │                       │        │          │
 │                          │   [Continuous Update] │        │          │
 │                          │◄───────────────────────┤        │          │
 │◄── Render UI ───────────│                       │        │          │
 │   (Roads, Lights,        │                       │        │          │
 │    Vehicles, Stats)      │                       │        │          │
 │                          │                       │        └──────────┘
 │                          │ Vehicle Execution (Executor Pool)
 │                          │    │
 │                          │    ├─ Check TrafficLight
 │                          │    ├─ Update Position
 │                          │    ├─ Check Intersection
 │                          │    ├─ Move/Wait/Exit
 │                          │    └─ Repeat
 │                          │
 │─ Click PAUSE ───────────→ Pause all threads
 │
 │─ Click RESET ───────────→ Reset simulation
 │
 └─ Close Window ──────────→ Shutdown executor

```

### 4.2 Traffic Light State Flow

```
         ┌─────────────────────┐
         │   RED STATE         │
         │  (Wait: 10 sec)     │
         └────────────┬────────┘
                      │ timeout
                      ▼
         ┌─────────────────────┐
         │  GREEN STATE        │
         │  (Move: 15 sec)     │
         └────────────┬────────┘
                      │ timeout
                      ▼
         ┌─────────────────────┐
         │  YELLOW STATE       │
         │  (Slow: 3 sec)      │
         └────────────┬────────┘
                      │ timeout
                      ▼
                   [RED STATE]
                      ▲
                      │
                   (Loop)
```

### 4.3 Vehicle Lifecycle

```
[Vehicle Created]
       │
       ▼
[Enter Intersection]
       │
       ├─ Check TrafficLight State
       │  │
       │  ├─ GREEN ─→ Move Forward
       │  ├─ YELLOW ─→ Prepare to Stop
       │  └─ RED ─→ Wait in Queue
       │
       ├─ Check for Collision
       │  │
       │  ├─ Clear ─→ Continue
       │  └─ Collision ─→ Throw CollisionException
       │
       ├─ Priority Check
       │  │
       │  ├─ Ambulance ─→ Skip Queue (Priority)
       │  └─ Normal ─→ Queue Management
       │
       ▼
[Exit Intersection]
       │
       ▼
[Vehicle Destroyed]
```

---

## 5. Key Features

### 5.1 GUI Components
✅ **Professional Swing UI**
- Real-time traffic intersection visualization
- Color-coded vehicles (Car-Blue, Truck-Orange, Motorbike-Purple, Ambulance-Red)
- Animated traffic lights (Green/Yellow/Red)
- Statistics panel (Time, Vehicles Passed, Waiting)
- Control buttons (START, PAUSE, RESET)
- Speed control options

### 5.2 Simulation Features
✅ **Multi-VehicleSimulation**
- 50 concurrent vehicles (ThreadPool)
- 3 types of standard vehicles
- Priority handling for ambulances
- Real-time collision detection
- Queue management at intersection

### 5.3 Thread Safety
✅ **Concurrent Execution**
- `AtomicReference` cho TrafficLight state
- `ConcurrentHashMap` cho vehicle storage
- `ExecutorService` (FixedThreadPool size 10)
- Synchronized blocks cho critical sections

### 5.4 Exception Handling
✅ **Custom Exceptions**
- `CollisionException` - Detect va chạm
- `TrafficJamException` - Detect kẹt xe

---

## 6. Class Diagram

```
┌─────────────────────────────────────────────────────────┐
│                    Vehicle (Abstract)                    │
│─────────────────────────────────────────────────────────│
│ - name: String                                          │
│ - speed: int                                            │
│ - intersection: Intersection                            │
│─────────────────────────────────────────────────────────│
│ + run(): void                                           │
│ + checkLight(): boolean                                 │
│ + moveForward(): void                                   │
│ + stop(): void                                          │
└──────────────────┬──────────────────┬───────────────────┘
                   │                  │
         ┌─────────▼──────┐  ┌────────▼──────────┐
         │ StandardVehicle│  │ PriorityVehicle   │
         │                │  │ (Ambulance)       │
         │ - type: String │  │ - priority: HIGH  │
         └────────────────┘  └───────────────────┘

┌──────────────────────────────────────────────────────┐
│              TrafficLight (Runnable)                 │
│──────────────────────────────────────────────────────│
│ - currentState: AtomicReference<State>              │
│ - State: {GREEN, YELLOW, RED}                       │
│──────────────────────────────────────────────────────│
│ + run(): void                                        │
│ + getState(): State                                  │
│ + setState(State): void                              │
└──────────────────────────────────────────────────────┘

┌──────────────────────────────────────────────────────┐
│              Intersection                            │
│──────────────────────────────────────────────────────│
│ - trafficLight: TrafficLight                        │
│ - queue: ConcurrentLinkedQueue                      │
│──────────────────────────────────────────────────────│
│ + canPass(): boolean                                 │
│ + enqueue(Vehicle): void                             │
│ + dequeue(): Vehicle                                 │
└──────────────────────────────────────────────────────┘
```

---

## 7. Setup & Execution

### 7.1 Yêu cầu
- **Java 8+**
- **IDE**: IntelliJ IDEA hoặc Eclipse
- **Build Tool**: Maven hoặc Gradle (optional)

### 7.2 Cấu trúc Thư mục
```
Session09/
├── engine/
│   ├── SimulationEngine.java
│   └── TrafficController.java
├── entity/
│   ├── Vehicle.java
│   ├── StandardVehicle.java
│   ├── PriorityVehicle.java
│   ├── TrafficLight.java
│   └── Intersection.java
├── exception/
│   ├── CollisionException.java
│   └── TrafficJamException.java
├── pattern/
│   ├── factory/
│   │   └── VehicleFactory.java
│   ├── observer/
│   │   ├── Observer.java
│   │   └── Subject.java
│   └── state/
│       ├── TrafficLightState.java
│       ├── GreenState.java
│       ├── YellowState.java
│       └── RedState.java
├── Ui/
│   ├── SimulatorWindow.java (Main GUI Entry)
│   └── SimulationUI.java (Canvas)
├── util/
│   ├── Logger.java
│   └── RandomUtil.java
├── test/
│   ├── IntersectionTest.java
│   └── TrafficLightTest.java
└── ReadMe.md
```

### 7.3 Chạy Ứng Dụng

**Option 1: Run từ IDE**
```bash
1. Open project in IDE
2. Navigate to: Session09.Ui.SimulatorWindow
3. Right-click → Run 'SimulatorWindow.main()'
4. GUI Window sẽ hiện lên
```

**Option 2: Compile & Run từ Terminal**
```bash
# Navigate to project directory
cd /path/to/Session09

# Compile
javac -d bin engine/*.java entity/*.java exception/*.java \
           pattern/*/*.java Ui/*.java util/*.java

# Run
java -cp bin Session09.Ui.SimulatorWindow
```

### 7.4 Sử dụng GUI
```
1. Click ▶ START - Bắt đầu mô phỏng
2. Xem giao diện:
   - 🚗 Vehicles chạy/chờ tại giao lộ
   - 🚦 Traffic Light hiển thị trạng thái hiện tại
   - 📊 Statistics cập nhật real-time
3. Click ⏸ PAUSE - Dừng tạm thời
4. Click 🔄 RESET - Khởi động lại mô phỏng
```

---

## 8. Testing

### 8.1 Unit Tests
- **IntersectionTest.java** - Test queue management, enqueue/dequeue
- **TrafficLightTest.java** - Test state transitions

### 8.2 Run Tests
```bash
# Using Maven
mvn test

# Using IDE
Right-click test file → Run Tests
```

---

## 9. Design Patterns Detailed

### 9.1 Factory Pattern
```java
VehicleFactory.createVehicle(
    type = "Car" | "Truck" | "Motorbike",
    speed,
    intersection
)
→ Returns StandardVehicle instance
```

### 9.2 Observer Pattern
```java
Subject.attach(observer)
Subject.notifyObservers()
→ Update subscribers when TrafficLight state changes
```

### 9.3 State Pattern
```java
TrafficLight.setState(GreenState | YellowState | RedState)
→ Different behavior for each state
```

---

## 10. Performance Metrics

| Metric | Value |
|--------|-------|
| **Vehicles** | 50 concurrent vehicles |
| **Thread Pool** | 10 fixed threads |
| **Update Rate** | 50ms (20 FPS UI) |
| **Traffic Light Cycle** | 28 seconds (15+3+10) |
| **Vehicle Generation** | 1 vehicle every 500ms |

---

## 11. Future Enhancements

- [ ] Multiple intersections network
- [ ] Pathfinding algorithm (A*)
- [ ] Weather effects
- [ ] Traffic accident simulation
- [ ] Vehicle GPS navigation
- [ ] Real-time statistics export (CSV, JSON)
- [ ] Traffic pattern analysis
- [ ] Machine learning for signal optimization

---

## 12. Authors & Contributors

- **IT203B Mini Project**
- Java Simulation & GUI Development
- Design Patterns & Multithreading Practice

---

**Last Updated**: March 2026  
**Status**: ✅ Production Ready  
**License**: Educational Use
- ReentrantLock
- Semaphore

Các kỹ thuật này giúp đảm bảo:

- Không xảy ra race condition
- Không xảy ra collision
- Hệ thống chạy ổn định với nhiều xe

---

### 2.4 Design Patterns

| Pattern | Mục đích |
|---|---|
| State Pattern | Quản lý trạng thái đèn giao thông |
| Observer Pattern | Xe nhận tín hiệu từ đèn giao thông |
| Factory Method | Sinh phương tiện ngẫu nhiên |

---

# 3. System Overview

Hệ thống mô phỏng một **giao lộ thông minh** với các thành phần chính:

- 🚦 Traffic Light System
- 🚗 Vehicle System
- 🧠 Simulation Engine
- 📊 Monitoring & Statistics

Quy trình mô phỏng:

```
Vehicle Factory → Vehicle Threads → Waiting Queue → Traffic Light Signal → Intersection Crossing → Statistics
```

---

# 4. Functional Requirements

## 4.1 Traffic Environment

Hệ thống tự động sinh ra các loại phương tiện:

| Vehicle Type | Priority | Description |
|---|---|---|
Motorbike | Low | Xe máy thông thường |
Car | Medium | Ô tô |
Truck | Medium | Xe tải |
Ambulance | High | Xe cứu thương |

Các phương tiện có:

- tốc độ khác nhau
- mức độ ưu tiên khác nhau
- hành vi khác nhau tại giao lộ

---

## 4.2 Traffic Light System

Đèn giao thông hoạt động theo chu kỳ:

```
Green → Yellow → Red → Green
```

Ví dụ thời gian:

| State | Duration |
|---|---|
Green | 10s |
Yellow | 3s |
Red | 10s |

Đèn chạy trên **Daemon Thread riêng biệt**.

---

## 4.3 Vehicle Movement

Mỗi phương tiện:

- chạy trên **1 thread riêng**
- di chuyển về phía ngã tư
- chờ trong hàng đợi nếu cần

Logic:

```
Nếu đèn đỏ → dừng
Nếu xe phía trước → chờ
Nếu đèn xanh → đi qua
```

---

## 4.4 Priority Handling

Xe cứu thương có **quyền ưu tiên**:

- Có thể **vượt đèn đỏ**
- Các xe khác phải **nhường đường**

---

## 4.5 Monitoring & Logging

Hệ thống in log realtime:

Ví dụ:

```
[Time: 12s] Car #15 is waiting at red light
[Time: 13s] Traffic Light turned GREEN
[Time: 14s] Ambulance #02 is crossing the intersection
```

---

## 4.6 Statistics

Hệ thống ghi nhận:

- Tổng số xe đi qua
- Số xe theo loại
- Số lần kẹt xe

Ví dụ:

```
Cars Passed: 45
Motorbikes: 72
Ambulances: 5
Traffic Jams: 2
```

---

# 5. Technical Requirements

## 5.1 Programming Language

```
Java 8+
```

Sử dụng:

- Streams API
- Lambda
- Concurrent Collections

---

## 5.2 Concurrent Collections

Các cấu trúc thread-safe được sử dụng:

```
ConcurrentLinkedQueue
BlockingQueue
CopyOnWriteArrayList
```

Ví dụ:

```java
BlockingQueue<Vehicle> waitingQueue = new LinkedBlockingQueue<>();
```

---

## 5.3 Synchronization

Intersection là **Critical Section**.

Chỉ có số lượng xe giới hạn được đi qua cùng lúc.

Các cơ chế sử dụng:

```
synchronized
ReentrantLock
Semaphore
```

Ví dụ:

```java
private final ReentrantLock intersectionLock = new ReentrantLock();
```

---

# 6. System Architecture

## 6.1 Project Structure

```
smart-traffic-simulator
│
├── entity
│   ├── Vehicle.java
│   ├── StandardVehicle.java
│   ├── PriorityVehicle.java
│   ├── TrafficLight.java
│   └── Intersection.java
│
├── engine
│   ├── SimulationEngine.java
│   ├── TrafficController.java
│
├── pattern
│   ├── state
│   │   ├── TrafficLightState.java
│   │   ├── GreenState.java
│   │   ├── YellowState.java
│   │   └── RedState.java
│   │
│   ├── observer
│   │   ├── Observer.java
│   │   ├── Subject.java
│   │
│   └── factory
│       └── VehicleFactory.java
│
├── exception
│   ├── TrafficJamException.java
│   └── CollisionException.java
│
├── util
│   ├── Logger.java
│   └── RandomUtil.java
│
└── test
    ├── TrafficLightTest.java
    ├── IntersectionTest.java
```

---

# 7. Design Patterns Implementation

## 7.1 State Pattern

Quản lý trạng thái của đèn giao thông.

Interface:

```
TrafficLightState
```

Các trạng thái:

```
GreenState
YellowState
RedState
```

Chuyển trạng thái:

```
Green → Yellow → Red → Green
```

---

## 7.2 Observer Pattern

Đèn giao thông là **Subject**.

Các xe là **Observers**.

Khi đèn thay đổi:

```
TrafficLight.notifyObservers()
```

Xe đang chờ sẽ quyết định:

```
go() hoặc stop()
```

---

## 7.3 Factory Method

VehicleFactory tạo phương tiện ngẫu nhiên:

```java
Vehicle vehicle = VehicleFactory.createRandomVehicle();
```

---

# 8. Multithreading Model

Hệ thống sử dụng:

```
ExecutorService Thread Pool
```

Ví dụ:

```java
ExecutorService executor = Executors.newFixedThreadPool(20);
```

Mỗi Vehicle:

```
implements Runnable
```

---

# 9. Exception Handling

## 9.1 TrafficJamException

Xảy ra khi hàng đợi vượt quá giới hạn.

```java
if(queue.size() > MAX_QUEUE)
throw new TrafficJamException();
```

---

## 9.2 CollisionException

Xảy ra nếu nhiều xe cùng vào intersection do lock lỗi.

---

# 10. Unit Testing

Framework:

```
JUnit 5
Mockito
```

Test cases:

### TrafficLight State Test

```
Green → Yellow → Red
```

---

### Multithreading Stress Test

Giả lập:

```
100 vehicles
enter intersection simultaneously
```

Kiểm tra:

```
No collision
No deadlock
```

---

# 11. Deadlock Handling Strategy

Deadlock có thể xảy ra khi nhiều xe cùng giữ lock.

Giải pháp:

### 1. Single Lock Strategy

Intersection chỉ có **1 lock trung tâm**

```
ReentrantLock intersectionLock
```

---

### 2. Timeout Lock

Sử dụng:

```
tryLock(timeout)
```

Nếu không lấy được lock:

```
vehicle waits again
```

---

### 3. Lane-Level Lock (Optimization)

Thay vì lock toàn bộ intersection:

```
Lane 1 Lock
Lane 2 Lock
Lane 3 Lock
Lane 4 Lock
```

Điều này tăng hiệu năng đáng kể.

---

# 12. Performance Optimization

Các tối ưu được đề xuất:

### Lock Granularity

Thay vì:

```
Lock toàn bộ intersection
```

Có thể dùng:

```
ReadWriteLock
Lane Lock
```

---

### Thread Pool

Không tạo thread vô hạn.

```
ExecutorService
```

---

### Non-blocking Queue

```
ConcurrentLinkedQueue
```

---

# 13. Example Console Output

```
[0s] Simulation started
[3s] Car #3 approaching intersection
[5s] Traffic Light: GREEN
[6s] Car #3 crossing intersection
[8s] Ambulance #1 approaching intersection
[9s] Ambulance #1 crossing (priority override)
[12s] Traffic Light: YELLOW
[15s] Traffic Light: RED
```

---

# 14. How to Run

### Clone project

```
git clone https://github.com/username/smart-traffic-simulator
```

### Compile

```
mvn clean install
```

or

```
gradle build
```

### Run

```
java -jar traffic-simulator.jar
```

---

# 15. Future Improvements

Các nâng cấp có thể thực hiện:

- GUI bằng JavaFX
- Mô phỏng nhiều ngã tư
- Machine Learning tối ưu đèn giao thông
- Visual traffic dashboard

---

# 16. Author

Mini Project – Smart Traffic Simulator  
Course: Concurrent Programming & Design Patterns

---

# 17. License

MIT License