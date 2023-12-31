# fast-rpc

### 设计思路

服务提供端 Server 向注册中心注册服务，服务消费者 Client 通过注册中心拿到服务相关信息，然后再通过网络请求服务提供端 Server。

1. Socket通信的步骤：
    - 创建服务器端Socket对象，并绑定服务器的IP地址和端口号。
    - 创建客户端Socket对象，并指定服务器的IP地址和端口号。
    - 服务器端调用accept()方法监听客户端的连接请求，并接受连接。
    - 客户端调用connect()方法向服务器端发起连接请求。
    - 服务器端和客户端建立连接后，通过输入流和输出流进行数据的传输。

2. Netty通信的步骤：
    - 创建一个引导类Bootstrap，用于启动Netty应用程序。
    - 创建一个EventLoopGroup，用于处理Channel的I/O操作。
    - 设置引导类的参数，如线程模型、Channel类型、TCP参数等。
    - 创建一个ChannelInitializer，用于初始化Channel的处理器。
    - 绑定服务器的IP地址和端口号，并启动服务器。

Netty相对于Socket的优势：
- 高性能：Netty使用了非阻塞I/O模型，能够处理大量并发连接，提高了系统的吞吐量和并发性能。
- 零拷贝：Netty通过使用直接内存缓冲区和零拷贝技术，减少了数据在应用程序和操作系统之间的数据拷贝次数，提高了数据传输效率。
- 内存管理：Netty使用了内存池技术，减少了内存的分配和回收操作，提高了内存的利用率和应用程序的性能。
- 线程模型：Netty使用了多线程模型，通过使用多个EventLoop并行处理I/O事件，提高了系统的并发性能。
- 协议支持：Netty提供了丰富的协议支持，可以轻松地开发基于HTTP、WebSocket、SSL等协议的应用程序。
- 容错和可靠性：Netty具有良好的容错和可靠性机制，可以处理网络异常和连接断开等情况，保证了应用程序的稳定性和可靠性。

### zookeeper
### SPI
### netty
EventLoopGroup是Netty中的一个重要概念，用于处理Channel的I/O操作。它实际上是一个线程池，由一组线程组成，每个线程都会处理一个或多个Channel的事件。

EventLoopGroup有两种类型：Boss EventLoopGroup和Worker EventLoopGroup。

1. Boss EventLoopGroup：
   - 负责接受客户端的连接请求，并将连接分配给Worker EventLoopGroup中的一个Worker EventLoop进行处理。
   - 通常只包含一个EventLoop线程。

2. Worker EventLoopGroup：
   - 负责处理已经建立的连接，处理读写等I/O操作。
   - 可以包含多个EventLoop线程，每个线程都会处理一个或多个Channel的事件。
   - 通过EventLoop的轮询机制，实现了事件的分发和处理。

EventLoopGroup的主要作用是管理EventLoop的生命周期，包括创建和销毁线程，以及处理事件的调度和分发。通过EventLoopGroup，Netty能够高效地处理大量并发连接，提高了系统的吞吐量和并发性能。

在创建引导类Bootstrap时，我们需要指定一个EventLoopGroup来处理Channel的I/O操作，通常会创建一个Boss EventLoopGroup和一个Worker EventLoopGroup，通过设置参数将它们关联起来。这样，当有客户端连接请求时，Boss EventLoopGroup接受连接并将其分配给Worker EventLoopGroup中的一个EventLoop进行处理。
### spring