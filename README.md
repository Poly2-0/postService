# MediaPost Application

A comprehensive Spring Boot-based social media platform with JWT authentication, media uploads, real-time interactions, and content moderation features.

## Features

### Core Functionality
- **User Management**: Complete user registration, authentication, and profile management
- **JWT Authentication**: Secure token-based authentication with 24-hour expiration
- **Post Creation**: Upload posts with images/videos and captions
- **Media Storage**: Advanced file upload system with unique naming and type detection
- **Like System**: Toggle like/unlike functionality with atomic counter updates
- **Comment System**: Hierarchical comments with automatic counter updates
- **Feed Retrieval**: Paginated feed with sorting and filtering options
- **Content Reporting**: Comprehensive content moderation and reporting system

### Advanced Features
- **Post Status Management**: Draft, Published, and Archived states
- **Security Layer**: JWT filtering, CORS configuration, and endpoint protection
- **File Validation**: Size and type validation for media uploads
- **Atomic Operations**: Thread-safe counter updates for likes and comments
- **RESTful APIs**: Clean REST endpoints with proper HTTP status codes
- **Error Handling**: Global exception handling with structured error responses

## Technology Stack

### Backend Technologies
- **Spring Boot 3.x**: Main application framework
- **Spring Web**: REST API development
- **Spring Data JPA**: Database operations with Hibernate
- **Spring Security**: Authentication and authorization framework
- **JWT**: Stateless authentication tokens

### Database & Storage
- **MySQL/PostgreSQL**: Primary database storage
- **Hibernate ORM**: Object-relational mapping
- **Local Filesystem**: Media file storage

### Development Tools
- **Lombok**: Boilerplate code reduction
- **ModelMapper**: DTO mapping utilities
- **Jakarta EE**: Enterprise Java standards
- **Maven**: Build and dependency management

### Security
- **BCrypt**: Password encryption
- **JWT Filtering**: Request authentication
- **CORS**: Cross-origin resource sharing

## Project Structure

```
PostService/
└── Post/
    ├── src/main/java/com/example/Post/
    │   ├── controller/          # REST API endpoints
    │   │   ├── PostController.java
    │   │   ├── CommentController.java
    │   │   ├── InteractionController.java
    │   │   ├── ReportController.java
    │   │   └── PostfetchController.java
    │   ├── service/             # Business logic interfaces
    │   │   ├── PostService.java
    │   │   ├── CommentService.java
    │   │   ├── InteractionService.java
    │   │   ├── ReportService.java
    │   │   ├── PostFetchService.java
    │   │   └── StorageService.java
    │   ├── service/Impl/        # Service implementations
    │   │   ├── PostServiceImpl.java
    │   │   ├── CommentServiceImpl.java
    │   │   ├── InteractionServiceImpl.java
    │   │   ├── ReportServiceImpl.java
    │   │   ├── PostFetchServiceImpl.java
    │   │   └── StorageServiceImpl.java
    │   ├── repository/          # Data access layer
    │   │   ├── PostRepository.java
    │   │   ├── CommentRepository.java
    │   │   ├── InteractionRepository.java
    │   │   └── ReportRepository.java
    │   ├── entity/              # JPA entities
    │   │   ├── Post.java
    │   │   ├── Comment.java
    │   │   ├── Interaction.java
    │   │   └── Report.java
    │   ├── DTO/                 # Data Transfer Objects
    │   │   ├── PostRequestDTO.java
    │   │   ├── PostResponseDTO.java
    │   │   └── InteractionRequest.java
    │   ├── config/              # Security configuration
    │   │   ├── SecurityConfig.java
    │   │   ├── JwtFilter.java
    │   │   └── JwtUtil.java
    │   ├── constant/            # Application constants
    │   │   ├── MediaType.java
    │   │   ├── InteractionType.java
    │   │   ├── PostStatus.java
    │   │   └── ReportCategory.java
    │   └── Configuration/       # Application configuration
    │       └── AppConfig.java
    └── pom.xml                  # Maven configuration
```

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login and JWT token generation
- `POST /api/users/create` - Create a new user account

### Post Management
- `POST /api/posts/upload` - Create a new post with media
- `GET /api/posts/feed` - Get paginated feed of posts
- `GET /api/posts/{id}` - Get specific post details

### Comments
- `POST /api/comments/add` - Add a comment to a post
- `GET /api/comments/post/{postId}` - Get paginated comments for a post

### Interactions
- `POST /api/Interactions/posts/{postId}/like/{userId}` - Toggle like/unlike on a post

### Content Moderation
- `POST /api/reports/create` - Report inappropriate content
- `GET /api/reports/post/{postId}` - Get all reports for a post

## Database Schema

### Users
- `id` - Primary key
- `username` - Unique username
- `email` - Email address
- `password` - Encrypted password (BCrypt)
- `createdAt` - Account creation timestamp

### Posts
- `id` - Primary key
- `caption` - Post caption/text
- `mediaUrl` - Path to uploaded media file
- `mediaType` - Type of media (IMAGE/VIDEO)
- `likeCount` - Number of likes
- `commentCount` - Number of comments
- `status` - Post status (DRAFT/PUBLISHED/ARCHIVED)
- `createdAt` - Post creation timestamp
- `author_id` - Foreign key to Users table

### Comments
- `id` - Primary key
- `text` - Comment content
- `createdAt` - Comment creation timestamp
- `user_id` - Foreign key to Users table
- `post_id` - Foreign key to Posts table

### Interactions
- `id` - Primary key
- `type` - Type of interaction (LIKE)
- `createdAt` - Interaction timestamp
- `user_id` - Foreign key to Users table
- `post_id` - Foreign key to Posts table
- `unique_user_post` - Composite unique constraint

### Reports
- `id` - Primary key
- `category` - Report category (INAPPROPRIATE_CONTENT/SPAM/HARASSMENT)
- `description` - Report description
- `createdAt` - Report creation timestamp
- `reporter_id` - Foreign key to Users table
- `post_id` - Foreign key to Posts table

## Key Features Implementation

### JWT Authentication System
- Token-based authentication with 24-hour expiration
- Secure password hashing with BCrypt
- JWT filter for request interception
- Role-based access control ready

### Media Upload System
- Files stored with timestamp-based unique names
- Support for images (JPEG, PNG, GIF, WebP) and videos (MP4, AVI, MOV)
- Automatic media type detection
- File size and type validation
- Static file serving from `/media/*` endpoint

### Like/Unlike Toggle Logic
- Atomic counter updates for thread safety
- Prevents duplicate likes with unique constraints
- Automatic counter decrement on unlike
- Optimized database queries with entity references

### Comment System
- Automatic comment count updates
- Paginated comment retrieval
- Entity references for efficient database operations
- Chronological ordering (newest first)

### Content Moderation
- Multi-category reporting system
- Report tracking and management
- Content status management
- Moderator-ready architecture

### Feed Pagination
- Spring Data pagination support
- Configurable page sizes and sorting
- Posts ordered by creation date (newest first)
- Efficient DTO mapping with media URL construction
- User-specific like status tracking

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- MySQL/PostgreSQL database

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd PostMedia/PostService/Post
```

2. Configure database and application settings in `application.properties`:
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/mediapost
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update

# JWT Configuration
app.jwt.secret=your-secret-key-here
app.jwt.expiration=86400000

# File Storage Configuration
app.storage.location=./uploads/
app.media.url-base=http://localhost:8080/media/
app.file.max-size=10MB

# Server Configuration
server.port=8080
```

3. Build and run the application:
```bash
./mvnw spring-boot:run
```

4. The application will start on `http://localhost:8080`

## Configuration

### File Storage
- Default storage location: `./uploads/`
- Configurable via application properties
- Automatic directory creation

### Media URL Base
- Default: `http://localhost:8080/media/`
- Configurable for different deployment environments

## Error Handling

The application implements comprehensive error handling:
- `RuntimeException` for resource not found scenarios
- File validation for empty uploads
- Counter safety checks to prevent negative values
- Proper HTTP status codes for API responses

## Security Implementation

### Authentication Flow
1. User registration/login with credentials
2. JWT token generation upon successful authentication
3. Token storage on client side
4. Token validation on each API request
5. Security context setup for authorized operations

### Security Features
- **JWT Tokens**: Stateless authentication with expiration
- **Password Encryption**: BCrypt hashing with strength 10
- **CORS Configuration**: Cross-origin request handling
- **Endpoint Protection**: Public vs. secured endpoint separation
- **Input Validation**: Comprehensive request validation

### Security Configuration
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // JWT filter configuration
    // Public endpoints: /api/auth/**, /api/users/create
    // Protected endpoints: All other APIs
}
```

## Performance Optimizations

- Efficient DTO mapping with ModelMapper
- Pagination for large datasets
- Entity references to avoid unnecessary database queries
- Atomic counter updates

## Future Enhancements

### Planned Features
- **User Profiles**: Extended profile management with avatars
- **Following System**: User follow/unfollow functionality
- **Real-time Notifications**: WebSocket-based notifications
- **Media Processing**: Image resizing, video thumbnails, compression
- **Search Functionality**: Full-text search with filters
- **Analytics Dashboard**: Post engagement and user analytics
- **Cloud Storage**: AWS S3 or similar integration
- **API Rate Limiting**: Prevent abuse and ensure fair usage

### Scalability Improvements
- **Microservices Architecture**: Service decomposition
- **Message Queues**: Asynchronous processing with RabbitMQ/Kafka
- **Load Balancing**: Horizontal scaling capabilities
- **Database Optimization**: Query optimization and indexing
- **Caching Layer**: Redis integration for performance
- **CDN Integration**: Global content delivery

## API Usage Examples

### User Registration
```bash
curl -X POST http://localhost:8080/api/users/create \
  -H "Content-Type: application/json" \
  -d '{"username":"john_doe","email":"john@example.com","password":"securePassword123"}'
```

### User Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"john_doe","password":"securePassword123"}'
```

### Create a Post (with JWT)
```bash
curl -X POST http://localhost:8080/api/posts/upload \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -F "caption=My first post" \
  -F "file=@image.jpg" \
  -F "userId=1"
```

### Like a Post (with JWT)
```bash
curl -X POST http://localhost:8080/api/Interactions/posts/1/like/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Add a Comment (with JWT)
```bash
curl -X POST http://localhost:8080/api/comments/add \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"postId":1,"userId":1,"text":"Great post!"}'
```

### Report Content (with JWT)
```bash
curl -X POST http://localhost:8080/api/reports/create \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"postId":1,"reporterId":2,"category":"INAPPROPRIATE_CONTENT","description":"Offensive content"}'
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Documentation

### Available Documentation
- **MediaPost_Application_Documentation.md**: Complete technical documentation
- **API Endpoints**: RESTful API specifications
- **Database Schema**: Entity relationships and structure
- **Security Implementation**: JWT authentication details

### Documentation Contents
- Complete API flow diagrams
- Service layer implementations
- Database operation details
- Architecture overview
- Security configuration
- Performance optimization strategies
- Future enhancement roadmap

### Additional Resources
- Spring Boot official documentation
- JWT authentication best practices
- REST API design guidelines
- Database optimization techniques
