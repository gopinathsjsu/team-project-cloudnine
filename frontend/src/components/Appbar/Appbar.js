import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import FitnessCenter from '@mui/icons-material/FitnessCenter';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import { FormControl, InputLabel, Select } from '@mui/material';

import { BrowserRouter, Link, Router, Navigate } from "react-router-dom";
import { Outlet } from "react-router-dom";

import AppContext from 'context';

import './Appbar.scss';
import Toast from 'components/Toast/Toast';
import util from 'util';

const settings = ['Profile', 'Account', 'Dashboard', 'Logout'];

function Appbar(props) {

  const pagesAdmin = [
    {
      title: 'Home',
      link: '/home',
    },
    {
      title: 'Profile',
      link: '/profile',
    },
    {
      title: 'Admin',
      link: '/admin',
    },
  ];

  const pagesNoAdmin = [
    {
      title: 'Home',
      link: '/home',
    },
    {
      title: 'Profile',
      link: '/profile',
    }
  ];

  // -------------------------

  const { context, setContext } = React.useContext(AppContext);

  const [anchorElNav, setAnchorElNav] = React.useState(null);
  const [anchorElUser, setAnchorElUser] = React.useState(null);

  const [selectedGym, setSelectedGym] = React.useState('');

  let [pages, setPages] = React.useState(pagesNoAdmin);

  const allGyms = ['Gym1', 'Gym2', 'Gym3'];

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const handleSelectGym = (e) => {
    util.setGym(e.target.value);
    setSelectedGym(e.target.value);
  }

  // -----------------

  React.useEffect(() => {
    setSelectedGym(util.getGym());
  }, []);

  React.useEffect(() => {

    console.log('context.isAdmin', context.isAdmin);

    if (context.isAdmin) {
      setPages(pagesAdmin);
      console.log('pages', pages);
    }
    else
      setPages(pagesNoAdmin);

  }, [context.isAdmin]);

  return (
    <div class="component-appbar">
      <Toast />
      <AppBar position="static">
        <Container maxWidth="xl">
          <Toolbar disableGutters>
            <FitnessCenter sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }} />
            <Typography
              variant="h6"
              noWrap
              component="a"
              href="/"
              sx={{
                mr: 2,
                display: { xs: 'none', md: 'flex' },
                fontFamily: 'monospace',
                fontWeight: 700,
                letterSpacing: '.3rem',
                color: 'inherit',
                textDecoration: 'none',
              }}
            >
              CARBCRUSHER
            </Typography>

            <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
              <IconButton
                size="large"
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={handleOpenNavMenu}
                color="inherit"
              >
                <MenuIcon />
              </IconButton>
              <Menu
                id="menu-appbar"
                anchorEl={anchorElNav}
                anchorOrigin={{
                  vertical: 'bottom',
                  horizontal: 'left',
                }}
                keepMounted
                transformOrigin={{
                  vertical: 'top',
                  horizontal: 'left',
                }}
                open={Boolean(anchorElNav)}
                onClose={handleCloseNavMenu}
                sx={{
                  display: { xs: 'block', md: 'none' },
                }}
              >
                {pages.map((page) => (
                  <Link to={page.link} style={{ textDecoration: 'none' }}>
                    <MenuItem key={page}>
                      <Typography textAlign="center">{page.title}</Typography>
                    </MenuItem>
                  </Link>
                ))}
              </Menu>
            </Box>
            <FitnessCenter sx={{ display: { xs: 'flex', md: 'none' }, mr: 1 }} />
            <Typography
              variant="h5"
              noWrap
              component="a"
              href=""
              sx={{
                mr: 2,
                display: { xs: 'flex', md: 'none' },
                flexGrow: 1,
                fontFamily: 'monospace',
                fontWeight: 700,
                letterSpacing: '.3rem',
                color: 'inherit',
                textDecoration: 'none',
              }}
            >
              CARDBCRUSHER
            </Typography>
            <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
              {pages.map((page) => (
                <Link to={page.link} style={{ textDecoration: 'none' }}>
                  <Button
                    key={page.title}
                    sx={{ my: 2, color: 'white', display: 'block' }}
                  >
                    {page.title}
                  </Button>
                </Link>

              ))}
            </Box>
            <FormControl sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
              <InputLabel size='small' id="select-label-class">Location</InputLabel>
              <Select
                size='small'
                labelId="select-label-class"
                value={selectedGym.toLowerCase()}
                label="Location"
                onChange={handleSelectGym}
              >
                {allGyms.map(gym =>
                  <MenuItem value={gym.toLowerCase()}>{gym}</MenuItem>
                )}
              </Select>
            </FormControl>

            {/* <Box sx={{ flexGrow: 0 }}>
            <Tooltip title="Open settings">
              <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                <Avatar alt="Remy Sharp" src="/static/images/avatar/2.jpg" />
              </IconButton>
            </Tooltip>
            <Menu
              sx={{ mt: '45px' }}
              id="menu-appbar"
              anchorEl={anchorElUser}
              anchorOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'right',
              }}
              open={Boolean(anchorElUser)}
              onClose={handleCloseUserMenu}
            >
              {settings.map((setting) => (
                <MenuItem key={setting} onClick={handleCloseUserMenu}>
                  <Typography textAlign="center">{setting}</Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box> */}
          </Toolbar>
        </Container>
      </AppBar>
      <div class="padded">
        <Breadcrumbs class="breadcrumbs" aria-label="breadcrumb">
          {/* <Link underline="hover" color="inherit" href="/">
            MUI
          </Link>
          <Link
            underline="hover"
            color="inherit"
            href="/material-ui/getting-started/installation/"
          >
            Core
          </Link> */}
          {context.path.map(p =>
            <Typography color="text.primary">{p}</Typography>
          )}
        </Breadcrumbs>
        <Box sx={{ p: 2 }}>
          <Outlet key={selectedGym} />
        </Box>
      </div>
    </div>
  );
}
export default Appbar;
