# MediaPost Application

A Spring Boot-based social media application that allows users to create posts with media uploads, interact with posts through likes and comments, and view a chronological feed.

## Features

- **User Management**: Create and manage user accounts
- **Post Creation**: Upload posts with images/videos and captions
- **Media Storage**: File upload system with unique naming
- **Like System**: Toggle like/unlike functionality on posts
- **Comment System**: Add comments to posts with automatic counter updates
- **Feed Retrieval**: Paginated feed showing posts in chronological order
- **RESTful APIs**: Clean REST endpoints for all operations

## Technology Stack

- **Backend**: Spring Boot 3.x
- **Database**: Spring Data JPA with Hibernate
- **File Storage**: Local filesystem storage
- **API Documentation**: RESTful design with proper HTTP status codes
- **Utilities**: 
  - Lombok for boilerplate reduction
  - ModelMapper for DTO mapping
  - Jakarta EE (formerly Java EE)

## Project Structure

```
PostService/
└── Post/
    ├── src/main/java/com/example/Post/
    │   ├── controller/          # REST API endpoints
    │   │   ├── PostController.java
    │   │   ├── UserController.java
    │   │   ├── CommentController.java
    │   │   └── InteractionController.java
    │   ├── service/             # Business logic layer
    │   │   ├── PostService.java
    │   │   ├── UserService.java
    │   │   ├── StorageService.java
    │   │   ├── CommentService.java
    │   │   └── InteractionService.java
    │   ├── service/Impl/        # Service implementations
    │   │   ├── PostServiceImpl.java
    │   │   ├── UserServiceImpl.java
    │   │   ├── StorageServiceImpl.java
    │   │   ├── CommentServiceImpl.java
    │   │   └── InteractionServiceImpl.java
    │   ├── repository/          # Data access layer
    │   │   ├── PostRepository.java
    │   │   ├── UserRepository.java
    │   │   ├── CommentRepository.java
    │   │   └── InteractionRepository.java
    │   ├── entity/              # JPA entities
    │   │   ├── Post.java
    │   │   ├── User.java
    │   │   ├── Comment.java
    │   │   └── Interaction.java
    │   ├── DTO/                 # Data Transfer Objects
    │   │   └── PostResponseDTO.java
    │   └── constant/            # Application constants
    │       └── MediaType.java
    └── pom.xml                  # Maven configuration
```

## API Endpoints

### User Management
- `POST /api/users/create` - Create a new user

### Post Management
- `POST /api/posts/upload` - Create a new post with media
- `GET /api/posts/feed` - Get paginated feed of posts

### Comments
- `POST /api/comments/add` - Add a comment to a post
- `GET /api/comments/post/{postId}` - Get all comments for a post

### Interactions
- `POST /api/Interactions/posts/{postId}/like` - Toggle like/unlike on a post

## Database Schema

### Users
- `id` - Primary key
- `username` - Unique username
- `email` - Email address
- `createdAt` - Account creation timestamp

### Posts
- `id` - Primary key
- `caption` - Post caption/text
- `mediaUrl` - Path to uploaded media file
- `mediaType` - Type of media (IMAGE/VIDEO)
- `likeCount` - Number of likes
- `commentCount` - Number of comments
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

## Key Features Implementation

### Media Upload System
- Files are stored with timestamp-based unique names
- Supports both images and videos
- Media type detection based on content type
- Static file serving from `/media/*` endpoint

### Like/Unlike Toggle Logic
- Atomic counter updates for like counts
- Prevents duplicate likes
- Automatic counter decrement on unlike

### Comment System
- Automatic comment count updates
- Entity references for efficient database operations
- Chronological ordering of comments

### Feed Pagination
- Spring Data pagination support
- Posts ordered by creation date (newest first)
- Efficient DTO mapping with media URL construction

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

2. Configure database in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mediapost
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
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

## Security Considerations

- File upload validation (size, type)
- SQL injection prevention via JPA
- Input validation on all endpoints
- Safe entity reference handling

## Performance Optimizations

- Efficient DTO mapping with ModelMapper
- Pagination for large datasets
- Entity references to avoid unnecessary database queries
- Atomic counter updates

## Future Enhancements

- User authentication and authorization
- Image/video processing and thumbnails
- Search functionality
- User profiles and following system
- Real-time notifications
- Cloud storage integration

## API Usage Examples

### Create a User
```bash
curl -X POST http://localhost:8080/api/users/create \
  -H "Content-Type: application/json" \
  -d '{"username":"john_doe","email":"john@example.com"}'
```

### Create a Post
```bash
curl -X POST http://localhost:8080/api/posts/upload \
  -F "caption=My first post" \
  -F "file=@image.jpg" \
  -F "userId=1"
```

### Like a Post
```bash
curl -X POST http://localhost:8080/api/Interactions/posts/1/like/1
```

### Add a Comment
```bash
curl -X POST http://localhost:8080/api/comments/add \
  -F "userId=1" \
  -F "postId=1" \
  -F "text=Great post!"
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

For detailed flow documentation, see `MediaPost_Application_Documentation.pdf` which contains:
- Complete API flow diagrams
- Service layer implementations
- Database operation details
- Architecture overview
- Technical specifications
