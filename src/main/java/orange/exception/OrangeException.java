    package orange.exception;
    /**
     * Represents a custom exception for handling errors specific to the Orange chatbot. This exception
     * is associated with an ExceptionType that provides an error code and message.
     *
     * @see ExceptionType
     */
    public class OrangeException extends Exception {
        /** The type of exception that occurred. */
        protected ExceptionType exception;

        /**
         * Constructs an OrangeException with a specified exception type.
         *
         * @param exception The specific exception type.
         */
        public OrangeException(ExceptionType exception) {
            super(exception.getMessage()); // Set message
            this.exception = exception;
        }

        /**
         * Retrieves the error message associated with this exception.
         *
         * @return The error message.
         */
        public String getCustomMessage() {
            return exception.getMessage();
        }

        /**
         * Retrieves the error code associated with this exception.
         *
         * @return The error code.
         */
        public int getExceptionCode() {
            return exception.getCode();
        }
    }
