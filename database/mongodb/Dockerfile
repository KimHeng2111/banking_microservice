# Use the official MongoDB image as base
FROM mongo:6.0

# Set environment variables (optional)
ENV MONGO_INITDB_ROOT_USERNAME=kimheng \
    MONGO_INITDB_ROOT_PASSWORD=kimheng0308

# Create a directory for MongoDB data
VOLUME /data/db

# Copy initialization scripts (optional)
# Any .js or .sh files placed here will run at startup
# Expose default MongoDB port
EXPOSE 27017

# Start MongoDB
CMD ["mongod"]
