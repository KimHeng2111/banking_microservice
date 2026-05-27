# Use official PostgreSQL base image
FROM postgres:16

# Set environment variables for default user and database
ENV POSTGRES_USER=loan_admin
ENV POSTGRES_PASSWORD=123456
ENV POSTGRES_DB=loan_db

# Create a volume for persistent data
VOLUME /var/lib/postgresql/data

# Copy initialization scripts (optional)
# Any .sql or .sh files placed here will run at startup
# COPY ./init-scripts/ /docker-entrypoint-initdb.d/

# Expose PostgreSQL default port
EXPOSE 5432

# Start PostgreSQL
CMD ["postgres"]
