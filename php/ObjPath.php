<?php

class ObjPath {

	private $nullObj;
	private $rootObj;
	private $workingObj;
	private $workingPath;

	private static function cleanPath($path) {
		if (is_string($path)) {
			$path = preg_replace('/\\/{2,}/', '/', trim($path));
			if (strlen($path) > 0)
				return $path;
		}
		return NULL;
	}

	public function __construct(&$obj) {
		$this->nullObj = NULL;
		$this->chroot($obj);
	}

	public function chroot(&$obj) {
		if (is_object($obj) || is_array($obj)) {
			$this->rootObj = &$obj;
			$result = TRUE;
		} else {
			$this->rootObj = &$this->nullObj;
			$result = FALSE;
		}
		$this->workingObj = &$this->rootObj;
		$this->workingPath = '/';
		return $result;
	}

	public function cd($path) {

		// check path
		$path = self::cleanPath($path);
		if ($path === NULL)
			return FALSE;

		// get reference (is absolute path?)
		if (strpos($path, '/') === 0) {
			$objRef = &$this->rootObj;
			$pathString = $path;
		} else {
			$objRef = &$this->workingObj;
			$pathString = $this->workingPath . $path;
		}

		$path = explode('/', trim($path, '/'));
		foreach ($path as $component) {

			if ($component === '' || $component === '.')
				continue;

			// check current component
			if (is_object($objRef) && property_exists($objRef, $component)) {
				$valRef = &$objRef->$component;
			} else if (is_array($objRef) && array_key_exists($component, $objRef)) {
				$valRef = &$objRef[$component];
			} else {
				$valRef = &$this->nullObj;
			}

			// evaluate value reference
			if (is_object($valRef) || is_array($valRef)) {
				$objRef = &$valRef;
			} else {
				$objRef = &$this->nullObj;
				break;
			}

		}

		if (is_object($objRef) || is_array($objRef)) {
			$this->workingObj  = &$objRef;
			if (strrpos($pathString, '/') !== strlen($pathString) - 1)
				$pathString .= '/';
			$this->workingPath = $pathString;
			return TRUE;
		}

		return FALSE;

	}

	public function get($path) {

		$value = NULL;

		// check path
		$path = self::cleanPath($path);
		if ($path === NULL)
			return $value;

		// get reference (is absolute path?)
		if (strpos($path, '/') === 0) {
			$objRef = &$this->rootObj;
		} else {
			$objRef = &$this->workingObj;
		}

		$path = explode('/', trim($path, '/'));
		foreach ($path as $component) {

			// check self referencing components...
			if ($component === '' || $component === '.')
				continue;

			// check current component
			if (is_object($objRef) && property_exists($objRef, $component)) {
				$valRef = &$objRef->$component;
			} else if (is_array($objRef) && array_key_exists($component, $objRef)) {
				$valRef = &$objRef[$component];
			} else {
				$valRef = &$this->nullObj;
			}

			// evaluate value reference
			if (is_object($valRef) || is_array($valRef)) {
				$objRef = &$valRef;
			} else {
				$objRef = &$this->nullObj;
				$value = $valRef;
			}

		}

		return $value;

	}

	public function ls($path) {

		$entries = array();

		// check path
		$path = self::cleanPath($path);
		if ($path === NULL)
			return $entries;

		// get reference (is absolute path?)
		if (strpos($path, '/') === 0) {
			$objRef = &$this->rootObj;
		} else {
			$objRef = &$this->workingObj;
		}

		$path = explode('/', trim($path, '/'));
		foreach ($path as $component) {

			if ($component === '' || $component === '.')
				continue;

			// check current component
			if (is_object($objRef) && property_exists($objRef, $component)) {
				$valRef = &$objRef->$component;
			} else if (is_array($objRef) && array_key_exists($component, $objRef)) {
				$valRef = &$objRef[$component];
			} else {
				$valRef = &$this->nullObj;
			}

			// evaluate value reference
			if (is_object($valRef) || is_array($valRef)) {
				$objRef = &$valRef;
			} else {
				$objRef = &$this->nullObj;
				break;
			}

		}

		if (is_object($objRef)) {
			$entries = array_keys(get_object_vars($objRef));
		} else if (is_array($objRef)) {
			$entries = array_keys($objRef);
		}

		return $entries;

	}

	public function pwd() {
		return $this->workingPath;
	}

}
