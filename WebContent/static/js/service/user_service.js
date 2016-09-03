'use strict';

angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){
	
	var restServiceUri = 'http://localhost:8080/SampleSpringMVCAngularHibernate/user/';
	
	var factory = {
		fetchAllUsers: fetchAllUsers,
	    createUser: createUser,
	    updateUser:updateUser,
	    deleteUser:deleteUser
	};
	
	return factory;
	
	function fetchAllUsers() {
		var deferred = $q.defer();
		$http.get(restServiceUri).then(function(response) {
			console.log('Successfully fecthed all users');
			deferred.resolve(response.data);
		}, function(errResponse) {
			console.log('error while retrieving users !!!');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
	function createUser(user) {
		var deferred = $q.defer();
		$http.post(restServiceUri, user).then(function(response) {
			console.log('Successfully Saved user : '+ user);
			deferred.resolve(response.data);
		}, function(errResponse) {
			console.log('error while saving user !!!');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
	function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteUser(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
}]);